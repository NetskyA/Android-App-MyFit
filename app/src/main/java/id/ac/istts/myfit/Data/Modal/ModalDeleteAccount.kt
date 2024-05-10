package id.ac.istts.myfit.Data.Modal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.ProfileSetting.MenuProfileSettingV2
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.databinding.ActivityMenuProfileSettingV2Binding
import id.ac.istts.myfit.databinding.ActivityModalDeleteAccountBinding

class ModalDeleteAccount : AppCompatActivity() {
    lateinit var binding: ActivityModalDeleteAccountBinding
    private lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modal_delete_account)
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