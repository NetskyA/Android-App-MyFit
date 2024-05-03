package id.ac.istts.myfit.LoginAll

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Spanned
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import id.ac.istts.myfit.HomeUser.HomeUserActivity
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.ActivityMenuLoginAllBinding

class MenuLoginAll : AppCompatActivity() {
    lateinit var binding: ActivityMenuLoginAllBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_login_all)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_login_all)

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val boldAndUnderlineText: Spanned = HtmlCompat.fromHtml("<u><b>Forgot password?</b></u>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.forgotpassword.text = boldAndUnderlineText
        binding.forgotpassword.setTypeface(null, Typeface.BOLD)


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
            startActivity(Intent(this, HomeUserActivity::class.java))
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