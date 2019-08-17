package com.thinkpalm.pixabay.network.models.photolist

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName


/**
 * Created by Jemsheer K D on 17 August, 2019.
 * Package com.thinkpalm.pixabay.network.models
 * Project Pixabay
 */
@Keep
data class PhotoListResponse(
        @SerializedName("hits")
        var photos: List<Photo> = listOf(),
        @SerializedName("total")
        var total: Int = 0,
        @SerializedName("totalHits")
        var totalHits: Int = 0
)

@Keep
data class Photo(
        @SerializedName("comments")
        var comments: Int = 0,
        @SerializedName("downloads")
        var downloads: Int = 0,
        @SerializedName("favorites")
        var favorites: Int = 0,
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("imageHeight")
        var imageHeight: Int = 0,
        @SerializedName("imageSize")
        var imageSize: Int = 0,
        @SerializedName("imageWidth")
        var imageWidth: Int = 0,
        @SerializedName("largeImageURL")
        var largeImageURL: String = "",
        @SerializedName("likes")
        var likes: Int = 0,
        @SerializedName("pageURL")
        var pageURL: String = "",
        @SerializedName("previewHeight")
        var previewHeight: Int = 0,
        @SerializedName("previewURL")
        var previewURL: String = "",
        @SerializedName("previewWidth")
        var previewWidth: Int = 0,
        @SerializedName("tags")
        var tags: String = "",
        @SerializedName("type")
        var type: String = "",
        @SerializedName("user")
        var user: String = "",
        @SerializedName("user_id")
        var userId: Int = 0,
        @SerializedName("userImageURL")
        var userImageURL: String = "",
        @SerializedName("views")
        var views: Int = 0,
        @SerializedName("webformatHeight")
        var webformatHeight: Int = 0,
        @SerializedName("webformatURL")
        var webformatURL: String = "",
        @SerializedName("webformatWidth")
        var webformatWidth: Int = 0
)