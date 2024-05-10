package id.ac.istts.myfit.ProfileSetting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentProfileSettingAccountBinding
import id.ac.istts.myfit.databinding.FragmentProfileSettingBodyBinding

class ProfileSettingBody : Fragment() {
    lateinit var binding: FragmentProfileSettingBodyBinding
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

    }
}