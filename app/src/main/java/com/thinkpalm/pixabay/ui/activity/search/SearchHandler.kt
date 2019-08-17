package com.thinkpalm.pixabay.ui.activity.search

import android.view.HapticFeedbackConstants
import android.view.View

/**
 * Created by Jemsheer K D on 21 June, 2019.
 * Package com.thinkpalm.pixabay.ui.activity.search
 * Project Pixabay
 */
class SearchHandler(var searchHandlerListener: SearchHandlerListener ) {

    /*fun performSearch(view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //mVibrator.vibrate(25);
        searchHandlerListener.performSearch(view)
    }*/


    interface SearchHandlerListener {
//        fun performSearch(view: View)
    }
}