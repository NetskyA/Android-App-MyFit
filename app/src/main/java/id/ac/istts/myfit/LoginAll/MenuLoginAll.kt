package id.ac.istts.myfit.LoginAll

import android.content.Context
import android.content.Intent
import android.content.IntentSender
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
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import id.ac.istts.myfit.HomeUser.HomeUserActivity
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignEmail.MenuSigninEmail
import id.ac.istts.myfit.SignPhone.MenuSigninPhone
import id.ac.istts.myfit.databinding.ActivityMenuLoginAllBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuLoginAll : AppCompatActivity() {
    lateinit var binding: ActivityMenuLoginAllBinding
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    lateinit var vm: MenuLoginAllViewModel
    lateinit var oneTapClient: SignInClient
    lateinit var signInRequest: BeginSignInRequest
    private var REQ_ONE_TAP = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_login_all)


        setupGoogleOneTap()


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

        binding.loginwithgoogle.setOnClickListener{
            startSignIn()
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
                            "No Internet Connection, Please check your connection",
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
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_ONE_TAP) {
            try {
                val credential = oneTapClient.getSignInCredentialFromIntent(data)
                val idToken = credential.googleIdToken
                val email = credential.id
                val phone = credential.phoneNumber
                val name = credential.displayName
                val profilePicUri = credential.profilePictureUri
                ioScope.launch {
                    val hasil = vm.checkEmail(email, name.orEmpty())
                    runOnUiThread {
                        if (hasil == "Ok") {
                            startActivity(Intent(this@MenuLoginAll, HomeUserActivity::class.java))
                            finish()
                        }else if(hasil == "Error"){
                            Toast.makeText(
                                this@MenuLoginAll,
                                "No Internet Connection, Please check your connection",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            startActivity(Intent(this@MenuLoginAll, MenuSigninEmail::class.java))
                        }
                    }
                }
            } catch (e: ApiException) {
                // Handle API exception
                Toast.makeText(this, "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
                Log.e("SignIn", e.toString() )
                Log.e("SignIn", "Sign in failed: ${e.statusCode}")
            }
        }
    }
    fun setupGoogleOneTap() {
        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                    .setSupported(false)
                    .build()
            )
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId("59220574093-aik5prg91c6fvsnp18r3kl2t58kag0kl.apps.googleusercontent.com")
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
    }

    fun startSignIn() {
        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener { result ->
                try {
                    startIntentSenderForResult(
                        result.pendingIntent.intentSender,
                        REQ_ONE_TAP,
                        null,
                        0,
                        0,
                        0
                    )
                } catch (e: IntentSender.SendIntentException) {
                    // Handle error
                    Toast.makeText(this, "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
                    Log.e("Error SignIn", e.toString())
                    Log.e("SignIn", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener {
                // Handle error
                Toast.makeText(this, "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
                Log.e("Fail SignIn", it.toString())
                Log.e("SignIn", "One Tap sign-in failed")
            }
    }
}