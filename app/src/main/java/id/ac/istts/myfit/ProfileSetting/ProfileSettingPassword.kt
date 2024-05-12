package id.ac.istts.myfit.ProfileSetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.databinding.FragmentProfileSettingPasswordBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileSettingPassword : Fragment() {
    lateinit var binding: FragmentProfileSettingPasswordBinding
    lateinit var vm: ProfileSettingPasswordViewModel
    private lateinit var userPreference: UserPreference
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileSettingPasswordBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this).get(ProfileSettingPasswordViewModel::class.java)

        vm.timerValue.observe(viewLifecycleOwner) { time ->
            if (time == -1) {
                binding.timerotp.text = "1 Minute"
            }else if(time==0){
                binding.timerotp.text = "Try Again"
            }else{
                binding.timerotp.text = "Time remaining: ${time} Seconds"
            }
        }

        binding.nextSignIn.setOnClickListener {
            val currentPass = binding.editprofilepassword.text.toString()
            val newPass = binding.editprofileneewpassword.text.toString()
            val cNewPass = binding.editprofileConfirmnewpassword.text.toString()
            ioScope.launch {
                val hasil = vm.cekPassword(currentPass, newPass, cNewPass)
                requireActivity().runOnUiThread {
                    if (hasil == "Empty") {
                        Toast.makeText(
                            context,
                            "Please fill all the field",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (hasil == "New Password and Confirm Password must be the same") {
                        Toast.makeText(context, hasil, Toast.LENGTH_SHORT).show()
                    } else if (hasil == "Invalid Password") {
                        Toast.makeText(
                            context,
                            "Password must contain at least 10 characters, 1 letter, and 1 number or special character",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (hasil == "Error") {
                        Toast.makeText(context, "Error, No Internet Connection", Toast.LENGTH_SHORT).show()
                    } else if (hasil == "Password not match") {
                        Toast.makeText(context, "Password not match", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "Password Changed", Toast.LENGTH_SHORT).show()
                        binding.timerotp.visibility = View.VISIBLE
                        binding.timerotp.text = "1 Minute"
                        binding.editprofileemailsendotp.hint = "Email to send OTP"
                        binding.changewraper.visibility = View.INVISIBLE
                    }
                }
            }
        }

        binding.sendrequestotp.setOnClickListener {
            ioScope.launch {
                val hasil = vm.sendOtp(binding.editprofileemailsendotp.text.toString())
                requireActivity().runOnUiThread {
                    if(hasil=="Error"){
                        Toast.makeText(context, "Error, No Internet Connection", Toast.LENGTH_SHORT).show()
                    }else if(hasil=="Invalid Email") {
                        Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show()
                    }else{
                        binding.editprofileemailsendotp.hint = "Input Otp"
                        binding.editprofileemailsendotp.setText("")
                        Toast.makeText(context, "Email Sent. Please Check Your Email", Toast.LENGTH_SHORT).show()
                        binding.sendrequestotp.setOnClickListener {
                            ioScope.launch {
                                val hasil1 = vm.cekOtp(binding.editprofileemailsendotp.text.toString())
                                requireActivity().runOnUiThread {
                                    if(hasil1=="Error"){
                                        Toast.makeText(context, "Error, No Internet Connection", Toast.LENGTH_SHORT).show()
                                    }else if(hasil1!="OTP is valid") {
                                        Toast.makeText(context,  hasil1, Toast.LENGTH_SHORT).show()
                                    }else{
                                        binding.changewraper.visibility = View.VISIBLE
                                        binding.timerotp.visibility = View.INVISIBLE
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}