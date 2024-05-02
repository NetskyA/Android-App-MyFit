package id.ac.istts.myfit.SignAll

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import id.ac.istts.myfit.LoginAll.MenuLoginAll
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignEmail.MenuSigninEmail
import id.ac.istts.myfit.SignPhone.MenuSigninPhone
import id.ac.istts.myfit.databinding.ActivityMenuSigninAllBinding

class MenuSigninAll : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninAllBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_signin_all)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_all)
/*        val boldAndUnderlineText: Spanned = HtmlCompat.fromHtml("<u><b>Use phone number instead.</b></u>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.signinwithphone.text = boldAndUnderlineText
        binding.signinwithphone.setTypeface(null, Typeface.BOLD)*/
        binding.signwithumberphone.setOnClickListener{
            startActivity(Intent(this, MenuSigninPhone::class.java))
        }
        binding.nextSignIn.setOnClickListener{
            startActivity(Intent(this, MenuSigninEmail::class.java))
        }
        binding.cheattoLogin.setOnClickListener{
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

        // Start the video as soon as it is ready
        binding.videoView.start()
    }
}