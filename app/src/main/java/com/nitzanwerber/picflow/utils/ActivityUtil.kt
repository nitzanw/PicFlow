package com.nitzanwerber.picflow.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.R
import com.nitzanwerber.picflow.views.PictureFlowFragment


class ActivityUtil {

    companion object Factory {
        fun create(): ActivityUtil = ActivityUtil()
    }

    fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {
        val currFragment = manager.findFragmentById(frameId)
        if(currFragment == null){
            val transaction = manager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }

    }
}