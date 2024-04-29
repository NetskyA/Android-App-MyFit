package com.example.myfit.MenuCustom

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myfit.R
import com.example.myfit.databinding.FragmentMenuCustomsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MenuCustoms.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuCustoms : Fragment() {
    lateinit var binding: FragmentMenuCustomsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
//        binding.etNamaMenuCustoms.setCompoundDrawablesWithIntrinsicBounds(R.drawable.namamenu, 0, 0, 0)
//        binding.etNamabahanbahan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ingredients, 0, 0, 0)
//        binding.etNamanutrisi.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nutrisi, 0, 0, 0)
//        binding.etCaraMemasak.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cooking, 0, 0, 0)
//        binding.etNotemenu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.note, 0, 0, 0)

        binding.btnAddCustoms.setOnClickListener {
            // Navigate
            val navHostFragment = childFragmentManager.findFragmentById(R.id.CustomsContainer) as NavHostFragment
            navHostFragment.findNavController().navigate(R.id.action_global_customsIngredients)

            // Change Active
            binding.btnAddCustoms.setBackgroundResource(R.drawable.backgroundnavigations4)
            binding.btnResultCustoms.setBackgroundColor(Color.parseColor("#0008C4D4"))
            binding.btnAddCustoms.setTextColor(resources.getColor(R.color.white))
            binding.btnResultCustoms.setTextColor(resources.getColor(R.color.blue_main))
        }

        binding.btnResultCustoms.setOnClickListener {
            // Navigate
            val navHostFragment = childFragmentManager.findFragmentById(R.id.CustomsContainer) as NavHostFragment
            navHostFragment.findNavController().navigate(R.id.action_global_customsResult)

            // Change Active
            binding.btnResultCustoms.setBackgroundResource(R.drawable.backgroundnavigations4)
            binding.btnAddCustoms.setBackgroundColor(Color.parseColor("#0008C4D4"))
            binding.btnResultCustoms.setTextColor(resources.getColor(R.color.white))
            binding.btnAddCustoms.setTextColor(resources.getColor(R.color.blue_main))
        }
    }

}