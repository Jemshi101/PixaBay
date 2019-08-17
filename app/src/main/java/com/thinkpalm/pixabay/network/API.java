package com.thinkpalm.pixabay.network;


import com.thinkpalm.pixabay.network.models.login.LoginRequest;
import com.thinkpalm.pixabay.network.models.login.LoginResponse;
import com.thinkpalm.pixabay.network.models.photolist.PhotoListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Candidate 1 on 10-04-2018.
 */

public interface API {



/*	@POST(ApiUrl.SEARCH_PACKAGES)
	Call<PackagesSearchResponse> searchPackages(@Header(Constants.AUTH) String authorization,
                                                @Header(Constants.USERID) int userId,
                                                @Header(ApiUrl.CURRECNCY_ID) String currency,
                                                @Body PackageSearchRequest packageSearchRequest);*/

    @POST(ApiUrl.LOGIN)
    Call<LoginResponse> performLogin(@Body LoginRequest request);

    @GET(ApiUrl.PHOTO_LIST)
    Call<PhotoListResponse> getPhotoList(@Query("key") String apiKey,
                                         @Query("q") String query,
                                         @Query("image_type") String imageType);


/*	@GET(ApiUrl.SAVE_PACKAGE_RECENTLY_VIEWED)
	Call<ResponseParent> savePackageRecentlyViewed(@Header(Constants.AUTH) String accessToken,
                                                   @Path("packageID") int packageID,
                                                   @Path("cityID") int cityID,
                                                   @Path("userID") int userID);*/


}