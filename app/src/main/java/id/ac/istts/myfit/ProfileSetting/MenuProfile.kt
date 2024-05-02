package com.example.myfit.ProfileSetting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.MenuFeed.MenuFeedOpened
import id.ac.istts.myfit.ProfileSetting.MenuProfileAdapter
import id.ac.istts.myfit.ProfileSetting.MenuProfileSettingV2
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuProfileBinding

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
        val rv_feedcontent: RecyclerView = requireView().findViewById(R.id.rv_feedcontent)

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_up)
        rv_feedcontent.startAnimation(fadeInAnimation)
        recyclerViewContent = binding.rvFeedcontent
        layoutManager = GridLayoutManager(context, 2)
        menuProfileAdapter = MenuProfileAdapter(temp, onDetailClickListener = {
            startActivity(Intent(this.context, MenuFeedOpened::class.java))
        })
        recyclerViewContent.adapter = menuProfileAdapter
        recyclerViewContent.layoutManager = layoutManager

        binding.lybar2.setOnClickListener {
//            val stepmove = MenuProfileDirections.actionMenuProfileToMenuProfileSeeting()
//            findNavController().navigate(stepmove)
            startActivity(Intent(this.context, MenuProfileSettingV2::class.java))
        }
    }
}