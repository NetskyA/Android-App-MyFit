package id.ac.istts.myfit.Data.Modal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.ProfileSetting.MenuProfileSettingV2
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.databinding.ActivityModalDeleteAccountBinding

class ModalDeleteAccount : AppCompatActivity() {

    private lateinit var binding: ActivityModalDeleteAccountBinding

    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityModalDeleteAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.canceldelete.setOnClickListener{
            userPreference.clearPref()
            startActivity(Intent(this, MenuProfileSettingV2::class.java))
        }

        binding.buttondeleteaccount.setOnClickListener{
            userPreference.clearPref()
            startActivity(Intent(this, MenuSigninAll::class.java))
        }
    }
}