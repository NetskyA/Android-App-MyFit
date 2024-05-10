package id.ac.istts.myfit.ProfileSetting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuFeed.MenuFeedOpened
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.ActivityMenuProfileSettingV2Binding
import id.ac.istts.myfit.databinding.FragmentMenuProfileBinding
import id.ac.istts.myfit.databinding.FragmentProfileSettingAccountBinding


class ProfileSettingAccount : Fragment() {
    lateinit var binding: FragmentProfileSettingAccountBinding
    private lateinit var userPreference: UserPreference
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreference = UserPreference(requireContext())
        Log.e("PREFERENCE", userPreference.getUser().toString())

        binding.editprofilename.setText(userPreference.getUser().name)
        binding.editprofilename.isEnabled = false
        binding.editprofileemail.setText(userPreference.getUser().email)
        binding.editprofileemail.isEnabled = false
        binding.editprofilepassword.setText(userPreference.getUser().password)
        binding.editprofilepassword.isEnabled = false
        binding.editprofilemobile.setText(userPreference.getUser().phone)
        binding.editprofilemobile.isEnabled = false

        var tempDate = userPreference.getUser().dob.toString()
        var date = tempDate.split("/")

        binding.etdaybirth.setText(date[0])
        binding.etdaybirth.isEnabled = false
        binding.etyearsbirth.setText(date[2])
        binding.etyearsbirth.isEnabled = false


    }
}