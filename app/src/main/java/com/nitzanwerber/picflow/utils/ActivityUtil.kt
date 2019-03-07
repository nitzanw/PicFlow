package com.nitzanwerber.picflow.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class ActivityUtil {


    companion object Factory {
        fun create(): ActivityUtil = ActivityUtil()
    }

    fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {

        val transaction = manager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }
}