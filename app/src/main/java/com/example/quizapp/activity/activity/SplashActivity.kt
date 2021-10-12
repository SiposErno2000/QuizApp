package com.example.quizapp.activity.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.quizapp.R

class SplashActivity : AppCompatActivity() {
    private lateinit var topAnimation: Animation
    private lateinit var lottieAnimation: Animation
    private lateinit var appName: TextView
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        createUIElements()
        loadAnimations()
        setAnimations()
        setAnimationListener()
    }

    private fun createUIElements() {
        appName = findViewById(R.id.app_name)
        lottieAnimationView = findViewById(R.id.lottie_animation)
    }

    private fun loadAnimations() {
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        lottieAnimation = AnimationUtils.loadAnimation(this, R.anim.lottie_animation)
    }

    private fun setAnimations() {
        appName.animation = topAnimation
        lottieAnimationView.animation = lottieAnimation
    }

    private fun setAnimationListener() {
        lottieAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }
}