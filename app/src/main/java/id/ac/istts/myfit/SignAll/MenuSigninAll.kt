package id.ac.istts.myfit.SignAll

import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import id.ac.istts.myfit.Data.Preferences.CustomMenuPreferences
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.HomeUser.HomeUserActivity
import id.ac.istts.myfit.LoginAll.MenuLoginAll
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignEmail.MenuSigninEmail
import id.ac.istts.myfit.SignPhone.MenuSigninPhone
import id.ac.istts.myfit.databinding.ActivityMenuSigninAllBinding

class MenuSigninAll : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninAllBinding
    lateinit var vm: MenuSigninAllViewModel
    lateinit var oneTapClient: SignInClient
    private lateinit var userPreference: UserPreference
    private lateinit var customMenuPreferences: CustomMenuPreferences
    lateinit var signInRequest: BeginSignInRequest
    private var REQ_ONE_TAP = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_signin_all)

        setupGoogleOneTap()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_all)
        /*        val boldAndUnderlineText: Spanned = HtmlCompat.fromHtml("<u><b>Use phone number instead.</b></u>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.signinwithphone.text = boldAndUnderlineText
        binding.signinwithphone.setTypeface(null, Typeface.BOLD)*/
        userPreference = UserPreference(this)
        customMenuPreferences = CustomMenuPreferences(this)
        Log.e("PREFERENCEPROFIL", userPreference.getUser().toString())
        Log.e("PREFERENCEPROFIL", customMenuPreferences.getCustomMenu().toString())
        if(userPreference.getUser().email != null && userPreference.getUser().email != ""){
            startActivity(Intent(this, HomeUserActivity::class.java))
            finish()
        }else{
            userPreference.clearPref()
        }
        binding.signwithumberphone.setOnClickListener {
            startActivity(Intent(this, MenuSigninPhone::class.java))
        }

        val linearLayout22 = binding.linearLayout22

        // Load and apply the animation
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_up)
        linearLayout22.startAnimation(fadeInAnimation)



        binding.nextSignIn.setOnClickListener {
            startActivity(Intent(this, MenuSigninEmail::class.java))
        }
        binding.linearLayouttoLogin.setOnClickListener {
            startActivity(Intent(this, MenuLoginAll::class.java))
        }

        val videoPath = "android.resource://" + packageName + "/" + R.raw.backgroundmovie2
        val uri = Uri.parse(videoPath)
        binding.videoView.setVideoURI(uri)

        // Set up a listener to loop the video continuously
        binding.videoView.setOnCompletionListener {
            // Restart the video when it completes
            binding.videoView.start()
        }

        binding.signwithgoogle.setOnClickListener {
            startSignIn()
        }
        // Start the video as soon as it is ready
        binding.videoView.start()

    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_ONE_TAP) {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    val email = credential.id
                    val phone = credential.displayName
                    Toast.makeText(this, "Halo", Toast.LENGTH_SHORT).show()
                    val profilePicUri = credential.profilePictureUri
                    Log.e("Hasil", phone.toString())
                    if (idToken != null) {
                        // Use the ID token to authenticate with your backend
                    } else {
                        // Handle sign-in error or retry logic
                    }
                } catch (e: ApiException) {
                    // Handle API exception
                    Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show()
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
                    .setServerClientId("59220574093-s6hk0lcq2sp2lfqaj522lbtjieer8aag.apps.googleusercontent.com")
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
                    Log.e("Error SignIn", e.toString())
                    Log.e("SignIn", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener {
                // Handle error
                Log.e("Fail SignIn", it.toString())
                Log.e("SignIn", "One Tap sign-in failed")
            }
    }
}