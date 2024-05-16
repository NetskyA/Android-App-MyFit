package id.ac.istts.myfit.MenuCustom

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuCustomsBinding



class MenuCustoms : Fragment() {
    lateinit var binding: FragmentMenuCustomsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuCustomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apajanih2: LinearLayout = requireView().findViewById(R.id.apajanih2)

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_up)
        apajanih2.startAnimation(fadeInAnimation)

        binding.btnAddCustoms.setOnClickListener {
            // Navigate
            val navHostFragment = childFragmentManager.findFragmentById(R.id.CustomsContainer) as NavHostFragment
            navHostFragment.findNavController().navigate(R.id.action_global_customsIngredients)

            // Change Active
            binding.btnAddCustoms.setBackgroundResource(R.drawable.backgroundnavigations4)
            binding.btnResultCustoms.setBackgroundColor(Color.parseColor("#0008C4D4"))
            binding.btnAddCustoms.setTextColor(resources.getColor(R.color.white))
            binding.btnResultCustoms.setTextColor(resources.getColor(R.color.green_main))
        }

        binding.btnResultCustoms.setOnClickListener {
            // Navigate
            val navHostFragment = childFragmentManager.findFragmentById(R.id.CustomsContainer) as NavHostFragment
            navHostFragment.findNavController().navigate(R.id.action_global_customsResult)

            // Change Active
            binding.btnResultCustoms.setBackgroundResource(R.drawable.backgroundnavigations3)
            binding.btnAddCustoms.setBackgroundColor(Color.parseColor("#0008C4D4"))
            binding.btnResultCustoms.setTextColor(resources.getColor(R.color.white))
            binding.btnAddCustoms.setTextColor(resources.getColor(R.color.green_main))
        }
    }

}