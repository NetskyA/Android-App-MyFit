package id.ac.istts.myfit.ProfileSetting

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentProfileSettingAccountBinding
import id.ac.istts.myfit.databinding.FragmentProfileSettingPasswordBinding


class ProfileSettingPassword : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentProfileSettingPasswordBinding

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

        binding.sendrequestotp.setOnClickListener {
            object : CountDownTimer(59000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.timerotp.text = "${millisUntilFinished / 1000} seconds"
                }

                override fun onFinish() {
                    binding.timerotp.text = "Try Again"
                }
            }.start()
        }
    }
}