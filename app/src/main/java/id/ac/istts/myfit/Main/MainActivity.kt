package id.ac.istts.myfit.Main

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, MenuSigninAll::class.java)
            startActivity(intent)
            finish()
        }, 1800)

        mediaPlayer = MediaPlayer.create(this, R.raw.marimba)
        mediaPlayer.start()

        val volume = 1.5f
        mediaPlayer.setVolume(volume, volume)
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}