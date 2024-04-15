package com.example.myfit.SignPhone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.myfit.R
import com.example.myfit.SignAll.MenuSigninAll
import com.example.myfit.databinding.ActivityMenuSigninAllBinding
import com.example.myfit.databinding.ActivityMenuSigninPhoneBinding

class MenuSigninPhone : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninPhoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_signin_phone)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_phone)
        val regionNames = resources.getStringArray(R.array.region_names)
        val regionIds = resources.getStringArray(R.array.region_ids)
        val combinedRegions = regionNames.zip(regionIds).map { "(${it.second}) ${it.first}" }

        val adapter = ArrayAdapter(this, R.layout.spinner_item, combinedRegions)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.numberRegions.adapter = adapter

        binding.nextSignInPhone.setOnClickListener{
            startActivity(Intent(this, MenuSigninPhone2::class.java))
        }

        binding.backToSignInPhone.setOnClickListener{
            startActivity(Intent(this, MenuSigninAll::class.java))
        }
    }
}