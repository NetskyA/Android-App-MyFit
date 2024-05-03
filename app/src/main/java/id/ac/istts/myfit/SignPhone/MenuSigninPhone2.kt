package id.ac.istts.myfit.SignPhone

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.SignEmail.MenuSigninEmail
import id.ac.istts.myfit.databinding.ActivityMenuSigninPhone2Binding

class MenuSigninPhone2 : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninPhone2Binding
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
        binding.nextSignInPhoneSixCode.setOnClickListener{
            startActivity(Intent(this, MenuSigninEmail::class.java))
        }

        binding.backToSignInPhone2.setOnClickListener{
            startActivity(Intent(this, MenuSigninAll::class.java))
        }
    }
}