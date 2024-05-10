package id.ac.istts.myfit.ProfileSetting

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuFeed.MenuFeedOpened
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignEmail.MenuSigninEmail2ViewModel
import id.ac.istts.myfit.SignEmail.MenuSigninEmail3
import id.ac.istts.myfit.Util.Month
import id.ac.istts.myfit.databinding.ActivityMenuProfileSettingV2Binding
import id.ac.istts.myfit.databinding.FragmentMenuProfileBinding
import id.ac.istts.myfit.databinding.FragmentProfileSettingAccountBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileSettingAccount : Fragment() {
    lateinit var binding: FragmentProfileSettingAccountBinding
    private lateinit var userPreference: UserPreference
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: EditProfileAccountViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileSettingAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreference = UserPreference(requireContext())
        Log.e("PREFERENCE", userPreference.getUser().toString())
        vm = ViewModelProvider(this).get(EditProfileAccountViewModel::class.java)

        val spinner: Spinner = binding.monthSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.months_array,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
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
        binding.editprofilename.setText(userPreference.getUser().name)
        binding.editprofilename.isEnabled = false
        binding.editprofileusernmae.setText(userPreference.getUser().username)
        binding.editprofileusernmae.isEnabled = false
        binding.editprofileemail.setText(userPreference.getUser().email)
        binding.editprofileemail.isEnabled = false
        binding.editprofilemobile.setText(userPreference.getUser().phone)
        binding.editprofilemobile.isEnabled = false

        var tempDate = userPreference.getUser().dob.toString()
        var date = tempDate.split("/")
        if(!tempDate.equals("")){
            binding.etdaybirth.setText(date[0])
            binding.etdaybirth.isEnabled = false
            binding.etyearsbirth.setText(date[2])
            binding.etyearsbirth.isEnabled = false
            spinner.setSelection(date[1].toInt()-1)
            spinner.isEnabled = false
//            Toast.makeText(requireContext(), tempMonth.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.nextSignIn.setOnClickListener{
            if(binding.nextSignIn.text.toString().equals("Edit Profile")){
                binding.nextSignIn.setText("Save")
                binding.editprofilename.isEnabled = true
                binding.editprofileemail.isEnabled = true
                binding.editprofileusernmae.isEnabled = true
                binding.editprofilemobile.isEnabled = true
                binding.etdaybirth.isEnabled = true
                binding.etyearsbirth.isEnabled = true
                spinner.isEnabled = true
            }else{
                binding.nextSignIn.setText("Edit Profile")
                binding.editprofilename.isEnabled = false
                binding.editprofileemail.isEnabled = false
                binding.editprofileusernmae.isEnabled = false
                binding.editprofilemobile.isEnabled = false
                binding.etdaybirth.isEnabled = false
                binding.etyearsbirth.isEnabled = false
                spinner.isEnabled = false
                ioScope.launch {
                    val hasil = vm.cekData(binding.editprofilename.text.toString(),
                        binding.editprofileusernmae.text.toString(), binding.editprofileemail.text.toString()
                        , binding.editprofilemobile.text.toString(), binding.etdaybirth.text.toString(),
                        spinner.selectedItem.toString(), binding.etyearsbirth.text.toString())
                    if(hasil=="Empty") {
                        mainScope.launch {
                            Toast.makeText(
                                requireContext(),
                                "Username, Email, and Mobile Phone are required",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }else if(hasil=="Same") {
                        mainScope.launch  {
                            Toast.makeText(
                                requireContext(),
                                "Email, Username, and Phone must be UNIQE",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }else if(hasil=="Invalid Date") {
                        mainScope.launch  {
                            Toast.makeText(
                                requireContext(),
                                "Invalid Date",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }else if(hasil=="Error"){
                        mainScope.launch{
                            Toast.makeText(
                                requireContext(),
                                "Error, No Internet Connection",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }else{
                        mainScope.launch{
                            Toast.makeText(
                                requireContext(),
                                "Edited Success",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    mainScope.launch {
                        tempDate = userPreference.getUser().dob.toString()
                        date = tempDate.split("/")
                        binding.etdaybirth.setText(date[0])
                        binding.etyearsbirth.setText(date[2])
                        spinner.setSelection(date[1].toInt()-1)
                        binding.editprofilename.setText(userPreference.getUser().name)
                        binding.editprofileusernmae.setText(userPreference.getUser().username)
                        binding.editprofileemail.setText(userPreference.getUser().email)
                        binding.editprofilemobile.setText(userPreference.getUser().phone)
                    }
                }
            }
        }

        binding.editprofilename.setCompoundDrawablesWithIntrinsicBounds(R.drawable.namamenu, 0, 0, 0)
        binding.editprofileemail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.emailimg, 0, 0, 0)
        binding.editprofileusernmae.setCompoundDrawablesWithIntrinsicBounds(R.drawable.passimg, 0, 0, 0)
        binding.editprofilemobile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.telephoneimg, 0, 0, 0)

    }
}