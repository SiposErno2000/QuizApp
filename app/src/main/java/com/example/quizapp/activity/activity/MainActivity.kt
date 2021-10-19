package com.example.quizapp.activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quizapp.R
import com.example.quizapp.activity.fragments.LoginFragment
import com.example.quizapp.activity.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStackImmediate()
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, LoginFragment()).commit()
    }

    override fun onBackPressed() {
        val fragmentList: List<Fragment> = supportFragmentManager.fragments

        val handled = false
        fragmentList.forEach {
            if (it is MainFragment) {
                it.onBackPressed()
            }
        }

        if (!handled) {
            super.onBackPressed()
        }
    }
}
