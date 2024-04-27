package com.example.myfit.ProfileSetting

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.example.myfit.Month
import com.example.myfit.R
import com.example.myfit.databinding.ActivityMenuProfileSettingV2Binding

class MenuProfileSettingV2 : AppCompatActivity() {

    lateinit var binding: ActivityMenuProfileSettingV2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityMenuProfileSettingV2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llPersonal.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingAccount)
        }

        binding.llBody.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingBody)
        }

     /*   binding.mpBtnBack.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_menuProfile)
        }*/
    }
}