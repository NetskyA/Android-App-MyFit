package id.ac.istts.myfit.SignPhone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.SignEmail.MenuSigninEmail
import id.ac.istts.myfit.SignEmail.MenuSigninEmail2
import id.ac.istts.myfit.databinding.ActivityMenuSigninPhoneBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuSigninPhone : AppCompatActivity() {
    lateinit var binding: ActivityMenuSigninPhoneBinding
    lateinit var vm: MenuSigninPhoneViewModel
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_signin_phone)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu_signin_phone)
        val regionNames = resources.getStringArray(R.array.region_names)
        val regionIds = resources.getStringArray(R.array.region_ids)
        val combinedRegions = regionNames.zip(regionIds).map { "(${it.second}) ${it.first}" }

        val adapter = ArrayAdapter(this, R.layout.spinner_item, combinedRegions)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.numberRegions.adapter = adapter

        vm = ViewModelProvider(this).get(MenuSigninPhoneViewModel::class.java)

        binding.nextSignInPhone.setOnClickListener{
            ioScope.launch {
                val hasil = vm.cekPhone(
                    binding.numberRegions.selectedItem.toString(),
                    binding.etinputnumberphone.text.toString()
                )
                runOnUiThread{
                    if(hasil=="Empty"){
                        binding.etinputnumberphone.requestFocus()
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(binding.etinputnumberphone, InputMethodManager.SHOW_IMPLICIT)
                        Toast.makeText(this@MenuSigninPhone, "Phone number cannot be empty", Toast.LENGTH_SHORT).show()
                    }else if(hasil=="Not Registered"){
                        startActivity(Intent(this@MenuSigninPhone, MenuSigninEmail::class.java))
                    }else if(hasil=="Error"){
                        Toast.makeText(
                            this@MenuSigninPhone,
                            "Error, No Internet Connection",
                            Toast.LENGTH_SHORT).show()
                    }else{
                        startActivity(Intent(this@MenuSigninPhone, MenuSigninPhone2::class.java))
                    }
                }
            }
        }

        binding.backToSignInPhone.setOnClickListener{
            startActivity(Intent(this, MenuSigninAll::class.java))
        }
    }
}