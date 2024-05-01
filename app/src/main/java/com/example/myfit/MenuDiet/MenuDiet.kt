package com.example.myfit.MenuDiet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfit.MenuFeed.MenuFeedOpened
import com.example.myfit.R
import com.example.myfit.databinding.FragmentMenuDietBinding

class MenuDiet : Fragment() {
    private lateinit var binding: FragmentMenuDietBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuDietBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView: ImageView = requireView().findViewById(R.id.menudietnih)
        val textWe: TextView = requireView().findViewById(R.id.tvTitleWe)
        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        imageView.startAnimation(fadeInAnimation)
        val fadeInAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        textWe.startAnimation(fadeInAnimation2)

        val menuDietLayoutIds = listOf(
            R.id.linearLayoutmenudiet1,
            R.id.linearLayoutmenudiet2,
            R.id.linearLayoutmenudiet3,
            R.id.linearLayoutmenudiet4,
            R.id.linearLayoutmenudiet5,
            R.id.linearLayoutmenudiet6,
            R.id.linearLayoutmenudiet7
        )

        // Load the animation once
        val fadeInAnimation1 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_up)

        // Apply the same animation to each ConstraintLayout found by ID
        menuDietLayoutIds.forEach { layoutId ->
            val layout = requireView().findViewById<ConstraintLayout>(layoutId)
            layout.startAnimation(fadeInAnimation1)
        }

        val temp: ArrayList<String> = arrayListOf("test", "test2", "test3", "test4")
        setupRecyclerView(binding.recyclerViewmenuResultDiet, temp, LinearLayoutManager.HORIZONTAL)
        setupRecyclerView(binding.recyclerViewmenuResultDiet2, temp, LinearLayoutManager.VERTICAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietThursday, temp, LinearLayoutManager.HORIZONTAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietThursday2, temp, LinearLayoutManager.VERTICAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietWednesday, temp, LinearLayoutManager.HORIZONTAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietWednesday2, temp, LinearLayoutManager.VERTICAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietTursday, temp, LinearLayoutManager.HORIZONTAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietTursday2, temp, LinearLayoutManager.VERTICAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietFriday, temp, LinearLayoutManager.HORIZONTAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietFriday2, temp, LinearLayoutManager.VERTICAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietSaturday, temp, LinearLayoutManager.HORIZONTAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietSaturday2, temp, LinearLayoutManager.VERTICAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietSunday, temp, LinearLayoutManager.HORIZONTAL)
        setupRecyclerView(binding.recyclerViewmenuResultDietSunday2, temp, LinearLayoutManager.VERTICAL)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, data: ArrayList<String>, orientation: Int) {
        val layoutManager = LinearLayoutManager(context, orientation, false)
        val adapter = if (orientation == LinearLayoutManager.HORIZONTAL) {
            MenuDietAdapter(data) { startActivity(Intent(context, MenuFeedOpened::class.java)) }
        } else {
            MenuDietSearchAdapter(data) { startActivity(Intent(context, MenuFeedOpened::class.java)) }
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
