package id.ac.istts.myfit.SignPhone

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.HomeUser.HomeUserActivity
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.SignEmail.MenuSigninEmail
import id.ac.istts.myfit.databinding.ActivityMenuSigninPhone2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuSigninPhone2 : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninPhone2Binding
    lateinit var vm: MenuSigninPhone2ViewModel
    private lateinit var userPreference: UserPreference
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_signin_phone2)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_phone2)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        vm = ViewModelProvider(this).get(MenuSigninPhone2ViewModel::class.java)
        ioScope.launch {
            val hasil = vm.sendOtp()
            if(hasil=="Error"){
                runOnUiThread {
                    Toast.makeText(this@MenuSigninPhone2, "Error, No Internet Connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
        vm.timerValue.observe(this) { time ->
            if(time == 0) {
                binding.textmsg1.text = "Get new codes?"
                binding.textmsg1.setOnClickListener {
                     ioScope.launch {
                        val hasil = vm.sendOtp()
                         runOnUiThread {
                             if(hasil=="Error"){
                                 Toast.makeText(this@MenuSigninPhone2, "Error, No Internet Connection", Toast.LENGTH_SHORT).show()
                             }else {
                                 Toast.makeText(this@MenuSigninPhone2, "Email Has Been Sent", Toast.LENGTH_SHORT)
                                     .show()
                             }
                         }
                    }
                }
            }else{
                binding.textmsg1.text = "Time remaining: ${time} Seconds"
            }
        }

        binding.nextSignInPhoneSixCode.setOnClickListener{
            ioScope.launch {
                val code = binding.etinputsixcode.text.toString()
                val hasil = vm.cekOtp(code)
                runOnUiThread {
                    if(hasil=="Error"){
                        Toast.makeText(this@MenuSigninPhone2, "Error, No Internet Connection", Toast.LENGTH_SHORT).show()
                    }else if(hasil!="OTP is valid"){
                        Toast.makeText(this@MenuSigninPhone2, hasil, Toast.LENGTH_SHORT).show()
                    }else{
                        startActivity(Intent(this@MenuSigninPhone2, HomeUserActivity::class.java))
                    }
                }
            }
        }

        binding.backToSignInPhone2.setOnClickListener{
            userPreference = UserPreference(this@MenuSigninPhone2)
            userPreference.clearPref()
            finish()
        }
    }
}