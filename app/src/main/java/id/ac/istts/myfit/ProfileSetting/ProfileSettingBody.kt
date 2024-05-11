package id.ac.istts.myfit.ProfileSetting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentProfileSettingAccountBinding
import id.ac.istts.myfit.databinding.FragmentProfileSettingBodyBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileSettingBody : Fragment() {
    lateinit var binding: FragmentProfileSettingBodyBinding
    private lateinit var userPreference: UserPreference
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: ProfileSettingBodyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileSettingBodyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editprofileheight.setCompoundDrawablesWithIntrinsicBounds(R.drawable.heightimg, 0, 0, 0)
        binding.editprofileweight.setCompoundDrawablesWithIntrinsicBounds(R.drawable.weightimg, 0, 0, 0)
        binding.editprofileage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ageimg, 0, 0, 0)
        binding.editprofilebloodtype.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bloodtype, 0, 0, 0)

        userPreference = UserPreference(requireContext())
//        Log.e("PREFERENCE", userPreference.getUser().toString())
        vm = ViewModelProvider(this).get(ProfileSettingBodyViewModel::class.java)

        binding.rbWoman.isEnabled = false
        binding.rbMan.isEnabled = false
        binding.rbNonBinary.isEnabled = false
        binding.rbWoman.isChecked = false
        binding.rbMan.isChecked = false
        binding.rbNonBinary.isChecked = false

        binding.editprofileage.isEnabled = false
        binding.editprofileheight.isEnabled = false
        binding.editprofileweight.isEnabled = false
        binding.editprofilebloodtype.isEnabled = false

        if(userPreference.getUser().gender==1){
            binding.rbNonBinary.isChecked = true
        }else if(userPreference.getUser().gender==2){
            binding.rbMan.isChecked = true
        }else{
            binding.rbWoman.isChecked = true
        }

        binding.editprofileheight.setText(userPreference.getUser().height.toString())
        binding.editprofileweight.setText(userPreference.getUser().weight.toString())
        binding.editprofileage.setText(userPreference.getUser().age.toString())
        binding.editprofilebloodtype.setText(userPreference.getUser().blood_type.toString())

        binding.nextSignIn.setOnClickListener{
            if(binding.nextSignIn.text=="Edit Profile"){
                binding.nextSignIn.setText("Save")
                binding.rbWoman.isEnabled = true
                binding.rbMan.isEnabled = true
                binding.rbNonBinary.isEnabled = true

                binding.editprofileheight.isEnabled = true
                binding.editprofileweight.isEnabled = true
                binding.editprofilebloodtype.isEnabled = true
            }else{
                binding.nextSignIn.setText("Edit Profile")

                val cekGender = if(binding.rbNonBinary.isChecked) "Non-binary" else if(binding.rbWoman.isChecked) "Female" else "Male"

                ioScope.launch {
                    val msg = vm.cekData(cekGender,binding.editprofileheight.text.toString(), binding.editprofileweight.text.toString(), binding.editprofilebloodtype.text.toString())
                    mainScope.launch {
                        if(msg=="Weight"){
                            Toast.makeText(requireContext(), "Invalid weight", Toast.LENGTH_SHORT).show()
                        }else if(msg=="Height"){
                            Toast.makeText(requireContext(), "Invalid height", Toast.LENGTH_SHORT).show()
                        }else if(msg=="Blood"){
                            Toast.makeText(requireContext(), "Invalid blood type", Toast.LENGTH_SHORT).show()
                        }else if(msg=="Error"){
                            Toast.makeText(requireContext(), "Error, No Internet Connection", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                binding.rbWoman.isEnabled = false
                binding.rbMan.isEnabled = false
                binding.rbNonBinary.isEnabled = false

                binding.editprofileage.isEnabled = false
                binding.editprofileheight.isEnabled = false
                binding.editprofileweight.isEnabled = false
                binding.editprofilebloodtype.isEnabled = false

                if(userPreference.getUser().gender==1){
                    binding.rbNonBinary.isChecked = true
                }else if(userPreference.getUser().gender==2){
                    binding.rbMan.isChecked = true
                }else{
                    binding.rbWoman.isChecked = true
                }

                binding.editprofileheight.setText(userPreference.getUser().height.toString())
                binding.editprofileweight.setText(userPreference.getUser().weight.toString())
                binding.editprofileage.setText(userPreference.getUser().age.toString())
                binding.editprofilebloodtype.setText(userPreference.getUser().blood_type.toString())
            }
        }
    }
}