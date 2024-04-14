package com.example.myfit.HomeUser

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myfit.R
import com.example.myfit.databinding.ActivityHomeUserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeUser : AppCompatActivity() {
    lateinit var binding: ActivityHomeUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_user)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_user)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {
                    // Load Home Fragment
                    true
                }
                R.id.item_2 -> {
                    // Load Dashboard Fragment
                    true
                }
                R.id.item_3 -> {
                    // Load Notifications Fragmentt
                    true
                }
                R.id.item_4 -> {
                    // Load Notifications Fragmentt
                    true
                }
                R.id.item_5 -> {
                    // Load Notifications Fragmentt
                    true
                }
                else -> false
            }
        }
        fun loadFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }

    }
}