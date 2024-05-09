package id.ac.istts.myfit.LoginAll

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Spanned
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.HomeUser.HomeUserActivity
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignPhone.MenuSigninPhone
import id.ac.istts.myfit.databinding.ActivityMenuLoginAllBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuLoginAll : AppCompatActivity() {
    lateinit var binding: ActivityMenuLoginAllBinding
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    lateinit var vm: MenuLoginAllViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_login_all)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_login_all)
        vm = ViewModelProvider(this).get(MenuLoginAllViewModel::class.java)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val boldAndUnderlineText: Spanned = HtmlCompat.fromHtml("<u><b>Forgot password?</b></u>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.forgotpassword.text = boldAndUnderlineText
        binding.forgotpassword.setTypeface(null, Typeface.BOLD)

        binding.loginwithphonenumber.setOnClickListener{
            startActivity(Intent(this, MenuSigninPhone::class.java))
            finish()
        }

        binding.materialSwitch.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                // The switch is checked.
                // Example: Change the background color to green
               /* binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_main))*/
            } else {
                // The switch isn't checked.
                // Example: Change the background color to white
                /*binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.white))*/
            }
        }

        binding.signuphere.setOnClickListener{
            /*startActivity(Intent(this, MenuSigninAll::class.java))*/
            finish()
        }

        binding.nextMenuLogIn.setOnClickListener{
            var data = binding.etusernameLogIn.text.toString()
            var password = binding.etPasswordLogin.text.toString()
            ioScope.launch {
                val hasil = vm.cekLogin(data, password)
                runOnUiThread {
                    if(hasil == "Empty") {
                        Toast.makeText(this@MenuLoginAll, "Please fill all the field", Toast.LENGTH_SHORT).show()
                    }else if(hasil == "User not found") {
                        binding.etusernameLogIn.requestFocus()
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(binding.etusernameLogIn, InputMethodManager.SHOW_IMPLICIT)

                        Toast.makeText(this@MenuLoginAll, "User not found", Toast.LENGTH_SHORT)
                                .show()

                    }else if(hasil == "Password not match") {
                        binding.etusernameLogIn.requestFocus()
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(binding.etusernameLogIn, InputMethodManager.SHOW_IMPLICIT)
                        Toast.makeText(
                                this@MenuLoginAll,
                                "Password not match",
                                Toast.LENGTH_SHORT)
                                .show()

                    }else if(hasil == "Error") {
                        Toast.makeText(
                            this@MenuLoginAll,
                            "Error, No Internet Connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        startActivity(Intent(this@MenuLoginAll, HomeUserActivity::class.java))
                        finish()
                    }
                }
            }
        }

        val videoPath = "android.resource://" + packageName + "/" + R.raw.backgroundmovie1
        val uri = Uri.parse(videoPath)
        binding.videoView1.setVideoURI(uri)
        binding.videoView1.setOnCompletionListener {
            binding.videoView1.start()
        }
        binding.videoView1.start()
    }
}