package id.ac.istts.myfit.SignEmail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.R
import id.ac.istts.myfit.Util.Month
import id.ac.istts.myfit.databinding.ActivityMenuSigninEmail2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MenuSigninEmail2 : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninEmail2Binding
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    lateinit var vm: MenuSigninEmail2ViewModel
    private lateinit var userPreference: UserPreference
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_email2)
        vm = ViewModelProvider(this).get(MenuSigninEmail2ViewModel::class.java)
        // Set up the spinner
        val spinner: Spinner = binding.monthSpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.months_array,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.setSelection(Month.April.index)

        binding.ButtonArrowBack.setOnClickListener {
            finish()
        }

        val ageObserver: Observer<Int> = Observer {
            binding.ettitleage.setText(it.toString())
        }
        vm.age.observe(this, ageObserver)
        userPreference = UserPreference(this)
        if(userPreference.getUser().name != null && userPreference.getUser().name != ""){
            binding.etNameUser.setText(userPreference.getUser().name)
        }

        binding.etdaybirth.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                vm.calculateAge(
                    binding.etdaybirth.text.toString(),
                    binding.monthSpinner.selectedItem.toString(),
                    binding.etyearsbirth.text.toString()
                )
            }
        }

        binding.etyearsbirth.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                vm.calculateAge(
                    binding.etdaybirth.text.toString(),
                    binding.monthSpinner.selectedItem.toString(),
                    binding.etyearsbirth.text.toString()
                )
            }
        }

        binding.nextSignInDateForm.setOnClickListener {
            val nama = binding.etNameUser.text.toString()
            val day = binding.etdaybirth.text.toString()
            val month = binding.monthSpinner.selectedItem.toString()
            val year = binding.etyearsbirth.text.toString()
            val gender = if (binding.rbMan.isChecked) "Male" else if (binding.rbWoman.isChecked) "Female" else if (binding.rbNonBinary.isChecked) "Non-binary" else ""
            val height = binding.ettitleheight.text.toString()
            val weight = binding.ettitleweight.text.toString()
            val bloodType = binding.ettitlebloodtype.text.toString()
            val allergy = binding.ettitlealaergi.text.toString()
            ioScope.launch {
                val hasil = vm.cekData(nama, day, month, year,
                    gender, weight, height, bloodType, allergy)
                if(hasil=="Empty") {
                    runOnUiThread {
                        Toast.makeText(
                            this@MenuSigninEmail2,
                            "Name, Date, and Gender are required",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else if(hasil=="Invalid Date") {
                    runOnUiThread {
                        Toast.makeText(
                            this@MenuSigninEmail2,
                            "Invalid Date",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else if(hasil=="Invalid Weight") {
                    binding.ettitleweight.requestFocus()
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(binding.ettitleweight, InputMethodManager.SHOW_IMPLICIT)
                    runOnUiThread {
                        Toast.makeText(
                            this@MenuSigninEmail2,
                            "Invalid Weight",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else if(hasil=="Invalid Height") {
                    binding.ettitleheight.requestFocus()
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(binding.ettitleheight, InputMethodManager.SHOW_IMPLICIT)
                    runOnUiThread {
                        Toast.makeText(
                            this@MenuSigninEmail2,
                            "Invalid Height",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else if(hasil=="Invalid Blood Type") {
                    binding.ettitlebloodtype.requestFocus()
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(binding.ettitlebloodtype, InputMethodManager.SHOW_IMPLICIT)
                    runOnUiThread {
                        Toast.makeText(
                            this@MenuSigninEmail2,
                            "Invalid Blood Type",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else if(hasil=="Error"){
                    Toast.makeText(
                        this@MenuSigninEmail2,
                        "No Internet Connection, Please check your connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    runOnUiThread {
                        startActivity(Intent(this@MenuSigninEmail2, MenuSigninEmail3::class.java))
                    }
                }
            }
        }

        val radioButtonsIds = arrayOf(R.id.rbMan, R.id.rbWoman, R.id.rbWoman, R.id.rbNonBinary)
        radioButtonsIds.forEach { radioButtonId ->
            val radioButton = findViewById<RadioButton>(radioButtonId)
            radioButton.setTextColor(ContextCompat.getColor(this, R.color.white))
            radioButton.buttonTintList = ContextCompat.getColorStateList(this, R.color.green_main)
        }
    }
}