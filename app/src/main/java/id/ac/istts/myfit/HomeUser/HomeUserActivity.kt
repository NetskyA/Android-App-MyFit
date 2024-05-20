package id.ac.istts.myfit.HomeUser

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationBarView
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.R

class HomeUserActivity : AppCompatActivity() {

    lateinit var container:FragmentContainerView
    private lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_user)
        container = findViewById(R.id.fragmentContainerView)

        userPreference = UserPreference(this)
        Log.e("Pref", userPreference.getUser().toString())

        val navigationBarView = findViewById<NavigationBarView>(R.id.bottom_navigation)
        navigationBarView.selectedItemId = R.id.Menu_Item_Feed
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