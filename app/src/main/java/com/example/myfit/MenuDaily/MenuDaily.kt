package com.example.myfit.MenuDaily

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.myfit.R
import com.example.myfit.databinding.FragmentMenuDailyBinding
import com.example.myfit.databinding.FragmentMenuDietBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.navigation.NavigationBarView


class MenuDaily : Fragment() {

    private lateinit var binding: FragmentMenuDailyBinding
    private lateinit var listAdapter: MenuDailyAdapter
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
        var data = mutableListOf<String>("test", "test2", "test3", "test4")
        val imageView: ImageView = requireView().findViewById(R.id.imageView7)
        val textWe: TextView = requireView().findViewById(R.id.tvTitleWe3)
        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        imageView.startAnimation(fadeInAnimation)

        val fadeInAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        textWe.startAnimation(fadeInAnimation2)


        /*        binding.carouselRecyclerView.layoutManager = CarouselLayoutManager()
                listAdapter = MenuDailyAdapter(data)
                binding.carouselRecyclerView.adapter = listAdapter*/

/*        val videoPath = "android.resource://" + requireActivity().packageName + "/" + R.raw.backgroundmovie1
        val uri = Uri.parse(videoPath)
        binding.videoView.setVideoURI(uri)

        // Set up a listener to loop the video continuously
        binding.videoView.setOnCompletionListener {
            // Restart the video when it completes
            binding.videoView.start()
        }

        // Start the video as soon as it is ready
        binding.videoView.start()*/

    }
}