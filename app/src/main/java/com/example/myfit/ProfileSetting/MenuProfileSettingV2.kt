package com.example.myfit.ProfileSetting

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.example.myfit.R
import com.example.myfit.databinding.ActivityMenuProfileSettingV2Binding

class MenuProfileSettingV2 : AppCompatActivity() {

    lateinit var container: FragmentContainerView
    private lateinit var binding: ActivityMenuProfileSettingV2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_profile_setting_v2)
        container = findViewById(R.id.ProfileFragmentContainer)

        binding = ActivityMenuProfileSettingV2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibAccount.setOnClickListener {
            Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show()
//            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingAccount)
            findNavController(container.id).navigate(R.id.action_global_profileSettingAccount)
        }

        binding.ibBody.setOnClickListener {
            Toast.makeText(this, "Body", Toast.LENGTH_SHORT).show()
            findNavController(container.id).navigate(R.id.action_global_profileSettingBody)
        }
    }
}