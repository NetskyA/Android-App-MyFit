package com.example.myfit.SignAll

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Spanned
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.myfit.LoginAll.MenuLoginAll
import com.example.myfit.R
import com.example.myfit.SignEmail.MenuSigninEmail
import com.example.myfit.SignPhone.MenuSigninPhone
import com.example.myfit.databinding.ActivityMainBinding
import com.example.myfit.databinding.ActivityMenuSigninAllBinding

class MenuSigninAll : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninAllBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_signin_all)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_all)
        val boldAndUnderlineText: Spanned = HtmlCompat.fromHtml("<u><b>Use phone number instead.</b></u>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.signinwithphone.text = boldAndUnderlineText
        binding.signinwithphone.setTypeface(null, Typeface.BOLD)
        binding.signinwithphone.setOnClickListener{
            startActivity(Intent(this, MenuSigninPhone::class.java))
        }
        binding.nextSignIn.setOnClickListener{
            startActivity(Intent(this, MenuSigninEmail::class.java))
        }
        binding.cheattoLogin.setOnClickListener{
            startActivity(Intent(this, MenuLoginAll::class.java))
        }

        val videoPath = "android.resource://" + packageName + "/" + R.raw.backgroundmovie
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