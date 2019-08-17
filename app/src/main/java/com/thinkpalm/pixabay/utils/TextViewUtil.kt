package com.thinkpalm.pixabay.utils

import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter


/**
 * Created by Jemsheer K D on 15 January, 2019.
 * Package com.thinkpalm.pixabay.utils
 * Project Pixabay
 */
class TextViewUtil {


    companion object {
        @JvmStatic
        @BindingAdapter("hashTags")
        fun setHashTags(textView: TextView, tags: String?) {
            tags?.let {
                val tagList = TextUtils.split(it, ", ")
                for (i in 0 until tagList.size) {
                    tagList[i] = "#${tagList[i]}"
                }
                val strTag = TextUtils.join(" ", tagList)
                textView.text = strTag
            } ?: kotlin.run {
                textView.text = ""
            }
        }

    }
}