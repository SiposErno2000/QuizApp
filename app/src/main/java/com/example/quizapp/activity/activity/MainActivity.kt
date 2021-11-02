package com.example.quizapp.activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quizapp.R
import com.example.quizapp.activity.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createUIElements()
    }

    private fun createUIElements() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.menu.getItem(0).isCheckable = true
        navigateToFragment(HomeFragment())

        bottomNavigation.setOnNavigationItemSelectedListener { menu ->
            when(menu.itemId) {
                R.id.home_fragment -> {
                    navigateToFragment(HomeFragment())
                    true
                }
                R.id.quiz_fragment -> {
                    navigateToFragment(QuizStartFragment())
                    true
                }
                R.id.profile_fragment -> {
                    navigateToFragment(ProfileFragment())
                    true
                }
                R.id.list_fragment -> {
                    navigateToFragment(ListFragment())
                    true
                }
                R.id.new_question_fragment -> {
                    navigateToFragment(CreateQuestionFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.main_fragment_container, fragment).commit()
    }

    override fun onBackPressed() {
        val fragmentList: List<Fragment> = supportFragmentManager.fragments

        val handled = false
        fragmentList.forEach {
            if (it is QuizStartFragment) {
                it.onBackPressed()
            }
        }

        if (!handled) {
            super.onBackPressed()
        }
    }
}
