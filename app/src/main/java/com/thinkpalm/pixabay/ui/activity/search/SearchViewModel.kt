package com.thinkpalm.pixabay.ui.activity.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.thinkpalm.pixabay.network.DataManager
import com.thinkpalm.pixabay.network.models.photolist.PhotoListResponse
import com.thinkpalm.pixabay.ui.core.BaseViewModel

/**
 * Created by Jemsheer K D on 17 August, 2019.
 * Package com.thinkpalm.pixabay.ui.activity.search
 * Project Orchid
 */
class SearchViewModel : BaseViewModel() {

    var photoListResponse = PhotoListResponse()
    var isGrid = false

    fun fetchPhotoList(): LiveData<PhotoListResponse> {

        return Transformations.map(DataManager.fetchPhotoList("Yellow Flowers")) {
            var response: PhotoListResponse? = null
            it?.let {
                if (it.status && it.responseBody != null) {
                    response = it.responseBody
                    photoListResponse = it.responseBody!!
                }
            }
            return@map response
        }
    }
}