package com.example.myfit.MenuCustom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfit.R
import com.example.myfit.databinding.FragmentMenuCustomsBinding
import com.example.myfit.databinding.FragmentMenuProfileBinding


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
        binding.etNamaMenuCustoms.setCompoundDrawablesWithIntrinsicBounds(R.drawable.namamenu, 0, 0, 0)
        binding.etNamabahanbahan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ingredients, 0, 0, 0)
        binding.etNamanutrisi.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nutrisi, 0, 0, 0)
        binding.etCaraMemasak.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cooking, 0, 0, 0)
        binding.etNotemenu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.note, 0, 0, 0)
    }
}