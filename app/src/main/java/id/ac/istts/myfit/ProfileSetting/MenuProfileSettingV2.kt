package id.ac.istts.myfit.ProfileSetting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.databinding.ActivityMenuProfileSettingV2Binding

class MenuProfileSettingV2 : AppCompatActivity() {

    lateinit var binding: ActivityMenuProfileSettingV2Binding
    private lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMenuProfileSettingV2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        userPreference = UserPreference(this)
//        Log.e("PREFERENCEPROFIL", userPreference.getUser().toString())
        binding.tvProfile.setText(userPreference.getUser().username)

        binding.button3.setOnClickListener{
            userPreference.clearPref()
            startActivity(Intent(this,MenuSigninAll::class.java))
        }
        binding.logoutFromProfileSetting.setOnClickListener {
            userPreference.clearPref()
            startActivity(Intent(this,MenuSigninAll::class.java))
        }

        binding.llPersonal.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingAccount)
        }

        binding.llBody.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingBody)
        }

        binding.ibAccount.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingAccount)
        }

        binding.ibBody.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingBody)
        }

        binding.mpBtnBack.setOnClickListener {
            finish()
        }
    }
}