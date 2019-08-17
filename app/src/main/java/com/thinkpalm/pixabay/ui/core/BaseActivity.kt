package com.thinkpalm.pixabay.ui.core

import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.thinkpalm.pixabay.R
import com.thinkpalm.pixabay.model.core.MessageBean

/**
 * Created by Jemsheer K D on 17 August, 2019.
 * Package com.thinkpalm.pixabay.ui.core
 * Project Pixabay
 */
abstract class BaseActivity : AppCompatActivity() {


    private val snackBarDismissOnClickListener: View.OnClickListener =
        View.OnClickListener { view ->
            view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            //mVibrator.vibrate(25);
            view.visibility = View.GONE
        }

    fun setProgressVisibilityStatus(progressVisibility: LiveData<Boolean>) {
        progressVisibility.observe(this, Observer<Boolean?> {
            it?.let {
                if (it) {

                } else {

                }
            }
        })
    }

    fun setMessageBox(view: View, message: LiveData<MessageBean>) {
        message.observe(this, Observer<MessageBean?> {
            it?.let {
                if (it.type == MessageBean.MESSAGE_TYPE_SNACK_BAR) {
                    Snackbar.make(view, it.message, it.time)
                        .setAction(R.string.btn_dismiss, snackBarDismissOnClickListener).show();
                } else {
                    Toast.makeText(applicationContext, it.message, it.time).show()
                }
            }
        })
    }

    // A method to find height of the status bar
    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getActionBarHeight(): Float {
        val mstyled = theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val mActionBarHeight = mstyled.getDimension(0, 0f)
        mstyled.recycle()
        return mActionBarHeight
    }
}
