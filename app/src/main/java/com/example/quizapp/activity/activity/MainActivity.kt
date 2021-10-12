package com.example.quizapp.activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.R
import com.example.quizapp.activity.fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStackImmediate()
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, LoginFragment()).commit()
    }
}
