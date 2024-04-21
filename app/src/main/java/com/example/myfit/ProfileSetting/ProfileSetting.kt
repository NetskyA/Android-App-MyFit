package com.example.myfit.ProfileSetting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myfit.R
import com.google.android.material.navigation.NavigationBarView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileSetting.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileSetting : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_profile_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigationBarView = view.findViewById<NavigationBarView>(R.id.bottom_navigation)
        navigationBarView.selectedItemId = R.id.Menu_Item_Daily
        navigationBarView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Menu_Item_Feed -> {
                    // Load Feed Fragment
                    true
                }
                R.id.Menu_Item_Custom -> {
                    // Load Custom Fragment
                    true
                }
                R.id.Menu_Item_Daily -> {
                    // Load Daily Fragment
                    // BIarkan kosong karena sudah berada di Daily Fragment
                    true
                }
                R.id.Menu_Item_Diet -> {
                    // Load Diet Fragment
                    findNavController().navigate(R.id.action_global_menuDiet)
                    true
                }
                R.id.Menu_Item_Setting -> {
                    // Load Settings Fragment
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileSetting.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileSetting().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}