package com.nitzanwerber.picflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityUtil.create().addFragmentToActivity(
            supportFragmentManager,  PictureFlowFragment.getInstance(), R.id.place_holder);

    }


}
