package com.example.myfit.ProfileSetting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfit.R
import com.example.myfit.databinding.FragmentMenuProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuProfile.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuProfile : Fragment() {
    private lateinit var binding: FragmentMenuProfileBinding
    private lateinit var recyclerViewContent: RecyclerView
    private lateinit var menuProfileAdapter: MenuProfileAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var temp:ArrayList<String> = arrayListOf("test", "test2", "test3","test", "test2", "test3","test", "test2", "test3","test", "test2", "test3")

        recyclerViewContent = binding.rvFeedcontent
        layoutManager = GridLayoutManager(context, 2)
        menuProfileAdapter = MenuProfileAdapter(temp)
        recyclerViewContent.adapter = menuProfileAdapter
        recyclerViewContent.layoutManager = layoutManager

        binding.lybar2.setOnClickListener {
            val stepmove = MenuProfileDirections.actionMenuProfileToMenuProfileSeeting()
            findNavController().navigate(stepmove)
        }

    }
}