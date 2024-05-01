package com.example.myfit.MenuDiet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfit.MenuFeed.MenuFeedOpened
import com.example.myfit.ProfileSetting.MenuProfileAdapter
import com.example.myfit.R
import com.example.myfit.databinding.FragmentMenuDietBinding
import com.example.myfit.databinding.FragmentMenuFeedsBinding
import com.google.android.material.navigation.NavigationBarView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuDiet.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuDiet : Fragment() {
    private lateinit var binding: FragmentMenuDietBinding
    private lateinit var recyclerViewContent: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var menuEditAdapter: MenuDietAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuDietBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var temp:ArrayList<String> = arrayListOf("test", "test2", "test3","test4" )
        recyclerViewContent = binding.recyclerViewmenuResultDiet
        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        menuEditAdapter = MenuDietAdapter(temp, onDetailClickListener = {
            startActivity(Intent(this.context, MenuFeedOpened::class.java))
        })
        recyclerViewContent.adapter = menuEditAdapter
        recyclerViewContent.layoutManager = layoutManager
    }

}