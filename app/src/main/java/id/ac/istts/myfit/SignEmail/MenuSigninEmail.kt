package id.ac.istts.myfit.SignEmail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.databinding.ActivityMenuSigninEmailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuSigninEmail : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninEmailBinding
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private lateinit var userPreference: UserPreference
    val vm: MenuSigninEmailViewModel by viewModels<MenuSigninEmailViewModel>()
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
            var username = binding.usernameSignIn.text.toString()
            var email = binding.userEmailSignIn.text.toString()
            var pass = binding.etPassword.text.toString()
            var cpass = binding.etConfirmPassword.text.toString()
            ioScope.launch {
                var hasil = vm.checkEmaiandUsername(email,username,pass,cpass)
                runOnUiThread {
                    if (hasil == "Empty") {
                        Toast.makeText(
                            this@MenuSigninEmail,
                            "Please fill all the field",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (hasil == "Password not match") {
                        Toast.makeText(this@MenuSigninEmail, hasil, Toast.LENGTH_SHORT).show()
                    } else if (hasil == "Invalid Password") {
                        binding.etPassword.requestFocus()
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(binding.etPassword, InputMethodManager.SHOW_IMPLICIT)
                        Toast.makeText(
                            this@MenuSigninEmail,
                            "Password must contain at least 10 characters, 1 letter, and 1 number or special character",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (hasil == "Invalid Email") {
                        binding.usernameSignIn.requestFocus()
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(binding.usernameSignIn, InputMethodManager.SHOW_IMPLICIT)
                        Toast.makeText(this@MenuSigninEmail, hasil, Toast.LENGTH_SHORT).show()
                    } else if (hasil == "Duplicate") {
                        Toast.makeText(
                            this@MenuSigninEmail,
                            "Duplicate Email or Username",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else if(hasil == "Error"){
                        Toast.makeText(
                            this@MenuSigninEmail,
                            "Error, No Internet Connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        userPreference = UserPreference(this@MenuSigninEmail)
                        userPreference.setRegisterEmail1(username,email,pass)
                        startActivity(Intent(this@MenuSigninEmail, MenuSigninEmail2::class.java))
                    }
                }
            }
        }
        binding.ButtonArrowBack.setOnClickListener{
            finish()
        }

    }
}