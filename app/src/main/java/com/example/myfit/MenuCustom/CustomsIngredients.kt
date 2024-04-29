package com.example.myfit.MenuCustom

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myfit.R
import com.example.myfit.databinding.FragmentCustomsIngredientsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomsIngredients.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomsIngredients : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentCustomsIngredientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_customs_ingredients, container, false)
        binding = FragmentCustomsIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextCheckmenu.setOnClickListener {
            // Move to Result Fragment
            findNavController().navigate(R.id.action_global_customsResult)

            // Change Active
            val navHostFragment = parentFragment as NavHostFragment
            val menuCustoms = navHostFragment.parentFragment as MenuCustoms
            menuCustoms.binding.btnResultCustoms.setBackgroundResource(R.drawable.backgroundnavigations4)
            menuCustoms.binding.btnAddCustoms.setBackgroundColor(Color.parseColor("#0008C4D4"))
            menuCustoms.binding.btnResultCustoms.setTextColor(resources.getColor(R.color.white))
            menuCustoms.binding.btnAddCustoms.setTextColor(resources.getColor(R.color.blue_main))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomsIngredients.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomsIngredients().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}