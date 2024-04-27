package com.example.myfit.MenuFeed

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myfit.databinding.ActivityMenuFeedOpenedBinding

class MenuFeedOpened : AppCompatActivity() {

    private lateinit var binding: ActivityMenuFeedOpenedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_menu_feed_opened)

        binding = ActivityMenuFeedOpenedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibAccount.setOnClickListener {
            finish()
        }
    }
}