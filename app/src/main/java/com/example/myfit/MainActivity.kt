package com.example.myfit

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myfit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, MenuLogin::class.java)
            startActivity(intent)
            finish() // Optional: Menutup MainActivity agar tidak kembali ke sini saat tombol kembali ditekan di MainActivity2
        }, 1800)

        mediaPlayer = MediaPlayer.create(this, R.raw.marimba)
        mediaPlayer.start()

        /*mediaPlayer = MediaPlayer.create(this, R.raw.soundopening)*/
        val volume = 0.6f
        mediaPlayer.setVolume(volume, volume)
       /* mediaPlayer.start()*/
    }
    override fun onDestroy() {
        super.onDestroy()
        // Release the MediaPlayer resources when the activity is destroyed
        mediaPlayer.release()
    }
}