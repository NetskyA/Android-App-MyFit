package com.example.myfit.HomeUser

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.myfit.R

class HomeUserActivity : AppCompatActivity() {

    lateinit var container:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_user)
        container = findViewById(R.id.fragmentContainer)
    }
}