package com.thinkpalm.pixabay.network;


import androidx.annotation.NonNull;

import com.google.android.gms.common.util.CollectionUtils;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.thinkpalm.pixabay.core.Config;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Candidate 1 on 10-04-2018.
 */

public class ApiClient {

    public static final String BASE_URL = "https://pixabay.com/";
    public static final String BASE_URL_WITH_API_VERSION = "https://pixabay.com/api";


    private static final long TIMEOUT_SECONDS = 45;

    private static Retrofit retrofit = null;
    private static Dispatcher dispatcher = null;
    private static OkHttpClient client = null;
    private API API_INSTANCE;

    private static ApiClient INSTANCE;


    private ApiClient() {

    }

    public static ApiClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public static void cancelAllConnections() {
        getOKHttpClient().dispatcher().cancelAll();
    }

    public static void reset() {
        cancelAllConnections();
        client = null;
        dispatcher = null;
        retrofit = null;
    }

    /**
     * Retrofit singleton instance used to call all the APIs throughout the project
     *
     * @return
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient client = getOKHttpClient();
            Gson gson = getGson();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientWithCustomBaseUrl(String baseUrl) {
        if (retrofit == null) {
            OkHttpClient client;
            if (!Config.isDebug) {
                //Logger
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client = new OkHttpClient.Builder().readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                okhttp3.Request.Builder requestBuilder = chain.request().newBuilder();
                                requestBuilder.header("Content-Type", "application/json");
                                return chain.proceed(requestBuilder.build());
                            }
                        }).connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).addInterceptor(interceptor).build();

            } else {
                client = new OkHttpClient.Builder().readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                okhttp3.Request.Builder requestBuilder = chain.request().newBuilder();
                                requestBuilder.header("Content-Type", "application/json");
                                return chain.proceed(requestBuilder.build());
                            }
                        }).build();

            }
            Gson gson = getGson();
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }

    @NonNull
    private static Gson getGson() {
        return new GsonBuilder()
                .setLenient()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {

                        Collection<Annotation> annotations = f.getAnnotations();
                        if (!CollectionUtils.isEmpty(annotations)) {
                            for (Annotation annotation : annotations) {
                                if (annotation instanceof SerializedName) {
                                    return false;
                                }
                            }
                        }
                        return true;
//						return f.getName().toLowerCase().contains("SerializedName".toLowerCase());

                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        Collection<Annotation> annotations = f.getAnnotations();
                        if (!CollectionUtils.isEmpty(annotations)) {
                            for (Annotation annotation : annotations) {
                                if (annotation instanceof SerializedName) {
                                    return false;
                                }
                            }
                        }
                        return true;
//						return f.getName().toLowerCase().contains("SerializedName".toLowerCase());
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .create();
    }

    @NonNull
    private static OkHttpClient getOKHttpClient() {
        if (client == null) {
            Dispatcher dispatcher = getDispatcher();
            if (!Config.isDebug) {
                client = new OkHttpClient.Builder().readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .dispatcher(dispatcher)
                        /*.addInterceptor(new CurlInterceptor())
                        .addInterceptor(interceptor)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                okhttp3.Request.Builder requestBuilder = chain.request().newBuilder();
                                requestBuilder.header("Content-Type", "application/json");
                                return chain.proceed(requestBuilder.build());
                            }
                        })*/.connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .build();
            } else {
                //Logger
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client = new OkHttpClient.Builder().readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .dispatcher(dispatcher)
                        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                okhttp3.Request.Builder requestBuilder = chain.request().newBuilder();
                                requestBuilder.header("Content-Type", "application/json");
                                return chain.proceed(requestBuilder.build());
                            }
                        })
                        .addInterceptor(interceptor)
                        .build();

            }
        }
        return client;
    }

    @NonNull
    private static Dispatcher getDispatcher() {
        if (dispatcher == null) {
            dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(150);
            dispatcher.setMaxRequestsPerHost(20);
        }
        return dispatcher;
    }

    public API getAPI() {
        if (API_INSTANCE == null) {
            API_INSTANCE = ApiClient.getClient().create(API.class);
        }
        return API_INSTANCE;
    }

}
