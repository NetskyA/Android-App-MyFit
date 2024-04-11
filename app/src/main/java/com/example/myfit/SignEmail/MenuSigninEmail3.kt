package com.example.myfit.SignEmail

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.myfit.HomeUser.HomeUser
import com.example.myfit.R
import com.example.myfit.databinding.ActivityMenuSigninEmail3Binding

class MenuSigninEmail3 : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninEmail3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_signin_email3)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_email3)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        */binding.hoverCheckBox1.setOnHoverListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_HOVER_ENTER -> {
                    // Do something when the hover enters
                }
                MotionEvent.ACTION_HOVER_EXIT -> {
                    // Do something when the hover exits
                }
            }
            true // Return true to indicate that the event was handled
        }
        binding.ButtonArrowBack.setOnClickListener{
            startActivity(Intent(this, MenuSigninEmail2::class.java))
        }
        binding.nextSignInSubmitTerms.setOnClickListener{
            startActivity(Intent(this, HomeUser::class.java))
        }
    }
}
