package com.example.myfit.HomeUser

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.example.myfit.MenuCustom.MenuCustoms
import com.example.myfit.MenuDaily.MenuDaily
import com.example.myfit.MenuDiet.MenuDiet
import com.example.myfit.MenuFeed.MenuFeeds
import com.example.myfit.ProfileSetting.MenuProfile
import com.example.myfit.R
import com.google.android.material.navigation.NavigationBarView

class HomeUserActivity : AppCompatActivity() {

    lateinit var container:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_user)
        container = findViewById(R.id.fragmentContainerView)

        val navigationBarView = findViewById<NavigationBarView>(R.id.bottom_navigation)
        navigationBarView.selectedItemId = R.id.Menu_Item_Daily
        navigationBarView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Menu_Item_Feed -> {
                    // Load Feed Fragment
                    findNavController(R.id.fragmentContainerView).navigate(R.id.action_global_menuFeeds2)
                    true
                }
                R.id.Menu_Item_Custom -> {
                    // Load Custom Fragment
                    findNavController(R.id.fragmentContainerView).navigate(R.id.action_global_menuCustoms)
                    true
                }
                R.id.Menu_Item_Daily -> {
                    // Load Daily Fragment
                    findNavController(R.id.fragmentContainerView).navigate(R.id.action_global_menuDaily)
                    true
                }
                R.id.Menu_Item_Diet -> {
                    // Load Diet Fragment
                    findNavController(R.id.fragmentContainerView).navigate(R.id.action_global_menuDiet)
                    true
                }
                R.id.Menu_Item_Setting -> {
                    // Load Settings Fragment
                    findNavController(R.id.fragmentContainerView).navigate(R.id.action_global_menuProfile)
                    true
                }
                else -> false
            }
        }
    }
}