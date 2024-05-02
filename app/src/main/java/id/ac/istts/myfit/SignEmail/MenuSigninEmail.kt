package id.ac.istts.myfit.SignEmail

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.databinding.ActivityMenuSigninEmailBinding

class MenuSigninEmail : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_signin_email)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_email)
        binding.nextSignInInputPassword.setOnClickListener{
            startActivity(Intent(this, MenuSigninEmail2::class.java))
        }
        binding.ButtonArrowBack.setOnClickListener{
            startActivity(Intent(this, MenuSigninAll::class.java))
        }
    }
}