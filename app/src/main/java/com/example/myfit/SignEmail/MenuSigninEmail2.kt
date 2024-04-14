package com.example.myfit.SignEmail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.myfit.Month
import com.example.myfit.R
import com.example.myfit.SignAll.MenuSigninAll
import com.example.myfit.databinding.ActivityMenuSigninEmail2Binding



class MenuSigninEmail2 : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninEmail2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_email2)

        // Set up the spinner
        val spinner: Spinner = binding.monthSpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.months_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.setSelection(Month.April.index)

        binding.ButtonArrowBack.setOnClickListener {
            startActivity(Intent(this, MenuSigninEmail::class.java))
        }
        binding.nextSignInDateForm.setOnClickListener {
            startActivity(Intent(this, MenuSigninEmail3::class.java))
        }

        val radioButtonsIds = arrayOf(R.id.rbMan, R.id.rbWoman, R.id.rbWoman, R.id.rbNonBinary, R.id.rbNotToSay)

        radioButtonsIds.forEach { radioButtonId ->
            val radioButton = findViewById<RadioButton>(radioButtonId)
            radioButton.setTextColor(ContextCompat.getColor(this, R.color.white))
            radioButton.buttonTintList = ContextCompat.getColorStateList(this, R.color.blue_main)
        }
    }

}