package com.example.myfit.MenuDiet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfit.MenuFeed.MenuFeedOpened
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
