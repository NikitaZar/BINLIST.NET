package ru.nikitazar.binlistnet.ui

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import dagger.hilt.android.AndroidEntryPoint
import ru.nikitazar.binlistnet.R
import ru.nikitazar.binlistnet.ui.view.LogoView

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logoAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.logo_anim)
        findViewById<LogoView>(R.id.myLogo).startAnimation(logoAnim)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }, 2000)
    }
}