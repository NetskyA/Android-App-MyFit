package id.ac.istts.myfit.SignEmail

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import id.ac.istts.myfit.HomeUser.HomeUserActivity
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.ActivityMenuSigninEmail3Binding

class MenuSigninEmail3 : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninEmail3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_signin_email3)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_email3)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        */binding.hoverCheckBox1.setOnHoverListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_HOVER_ENTER -> {
                    // Do something when the hover enters
                }
                MotionEvent.ACTION_HOVER_EXIT -> {
                    // Do something when the hover exits
                }
            }
            true // Return true to indicate that the event was handled
        }
        binding.ButtonArrowBack.setOnClickListener{
            finish()
        }
        binding.nextFinishSignInSubmitTerms.setOnClickListener{
            startActivity(Intent(this, HomeUserActivity::class.java))
        }

    }
}
