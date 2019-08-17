package com.thinkpalm.pixabay.utils

import androidx.databinding.BindingAdapter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.thinkpalm.pixabay.R


/**
 * Created by Jemsheer K D on 15 January, 2019.
 * Package com.thinkpalm.pixabay.utils
 * Project Pixabay
 */
class ImageViewUtil {


	companion object {
		@JvmStatic
		@BindingAdapter("imageUrl")
		fun setImageUrl(imageView: ImageView, imagePath: String) {
			Glide.with(imageView.context.applicationContext)
				.load(NullCheckUtil.getNonNull(imagePath))
				.apply(
					RequestOptions()
						.placeholder(R.drawable.placeholder_image)
						.error(R.drawable.placeholder_image)
						.fallback(R.drawable.placeholder_image)
						.centerCrop()
				)
				.transition(DrawableTransitionOptions.withCrossFade())
				.into(imageView)
		}

		@JvmStatic
		@BindingAdapter("imageCircleUrl")
		fun setCircleImageUrl(imageView: ImageView, imagePath: String) {
			Glide.with(imageView.context.applicationContext)
				.load(NullCheckUtil.getNonNull(imagePath))
				.apply(
					RequestOptions()
						.placeholder(R.drawable.placeholder_image)
						.error(R.drawable.placeholder_image)
						.fallback(R.drawable.placeholder_image)
						.centerCrop()
						.circleCrop()
				)
				.transition(DrawableTransitionOptions.withCrossFade())
				.into(imageView)
		}

		@JvmStatic
		@BindingAdapter("imageUrl")
		fun setImageUrl(imageView: ImageView, imageRes: Int) {
			Glide.with(imageView.context.applicationContext)
				.load(imageRes)
				.apply(
					RequestOptions()
						.placeholder(R.drawable.placeholder_image)
						.error(R.drawable.placeholder_image)
						.fallback(R.drawable.placeholder_image)
						.centerCrop()
				)
				.transition(DrawableTransitionOptions.withCrossFade())
				.into(imageView)
		}

		@JvmStatic
		@BindingAdapter("imageUrl")
		fun setImageUrl(imageView: ImageView, imageDrawable: Drawable) {
			Glide.with(imageView.context.applicationContext)
				.load(imageDrawable)
				.apply(
					RequestOptions()
						.placeholder(R.drawable.placeholder_image)
						.error(R.drawable.placeholder_image)
						.fallback(R.drawable.placeholder_image)
						.centerCrop()
				)
				.transition(DrawableTransitionOptions.withCrossFade())
				.into(imageView)
		}

		@JvmStatic
		@BindingAdapter("bind:tintColor")
		fun setColorTint(view: ImageView, @ColorRes color: Int) {
			view.setColorFilter(color, PorterDuff.Mode.SRC_IN)
			//			DrawableCompat.setTint(view.drawable, color)
		}

		@JvmStatic
		@BindingAdapter("app:srcCompat")
		fun setImageResource(view: ImageView, @DrawableRes drawable: Int) {
			view.setImageResource(drawable)
			//			DrawableCompat.setTint(view.drawable, color)
		}
	}
}