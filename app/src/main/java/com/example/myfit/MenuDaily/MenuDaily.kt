package com.example.myfit.MenuDaily

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfit.MenuFeed.MenuFeedOpened
import com.example.myfit.ProfileSetting.MenuProfileAdapter
import com.example.myfit.R
import com.example.myfit.databinding.FragmentMenuDailyBinding
import com.example.myfit.databinding.FragmentMenuDietBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.navigation.NavigationBarView


class MenuDaily : Fragment() {

    private lateinit var binding: FragmentMenuDailyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var temp:ArrayList<String> = arrayListOf("test", "test2", "test3","test", "test2", "test3","test", "test2", "test3","test", "test2", "test3")

        val imageView: ImageView = requireView().findViewById(R.id.imageView7)
        val textWe: TextView = requireView().findViewById(R.id.tvTitleWe3)
        val clycontentmenutoday: ConstraintLayout = requireView().findViewById(R.id.lycontentmenutoday)

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_right)
        imageView.startAnimation(fadeInAnimation)

        val fadeInAnimation1 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        clycontentmenutoday.startAnimation(fadeInAnimation1)

        val fadeInAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_left)
        textWe.startAnimation(fadeInAnimation2)

    }
}