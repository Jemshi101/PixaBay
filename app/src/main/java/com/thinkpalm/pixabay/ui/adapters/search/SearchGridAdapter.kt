package com.thinkpalm.pixabay.ui.adapters.search

import android.app.Activity
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thinkpalm.pixabay.R
import com.thinkpalm.pixabay.databinding.ItemListPhotoBinding
import com.thinkpalm.pixabay.network.models.photolist.PhotoListResponse

/**
 * Created by Jemsheer K D on 17 August, 2019.
 * Package com.thinkpalm.pixabay.ui.adapters.search
 * Project Orchid
 */
class SearchGridAdapter(var mContext: Activity,
                        var photoListResponse: PhotoListResponse,
                        mListener: SearchGridAdapterListener
) : RecyclerView.Adapter<SearchGridAdapter.ViewHolder>() {

    private val TAG = "SearchGridAda"
    var isLoadMore: Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemListPhotoBinding>(
                inflater, R.layout.item_list_photo, parent, false)

        return ViewHolder(binding)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        // dummy count.. to be changed.
        val count: Int
        try {
            count = photoListResponse.photos.size
        } catch (e: Exception) {
            return 0
        }

        return count
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder(var binding: ItemListPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { view ->
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                //                    mVibrator.vibrate(25);
                val bean = photoListResponse.photos[layoutPosition]
                /* mContext.startActivity(new Intent(mContext, DetailsActivity.class)
                        .putExtra("bean", bean));*/
            }
        }

        fun bind(position: Int) {
            val bean = photoListResponse.photos.get(position)
            binding.bean = bean
        }

    }

    interface SearchGridAdapterListener {
        fun onRequestNextPage(isLoadMore: Boolean, currentPageNumber: Int)
        fun onRefresh()
        fun onSwipeRefreshingChange(isSwipeRefreshing: Boolean)
        fun onSnackBarShow(message: String)
    }

}