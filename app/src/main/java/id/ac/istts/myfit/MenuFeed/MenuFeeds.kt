package id.ac.istts.myfit.MenuFeed

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.ProfileSetting.MenuProfileAdapter
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuFeedsBinding

class MenuFeeds : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentMenuFeedsBinding
    private lateinit var recyclerViewContent: RecyclerView
    private lateinit var menuProfileAdapter: MenuProfileAdapter
    private lateinit var layoutManagerContent: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuFeedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Isi RV sementara
        var temp:ArrayList<String> = arrayListOf("test", "test2", "test3","test", "test2", "test3","test", "test2", "test3","test", "test2", "test3","test", "test2", "test3","test", "test2", "test3")

        recyclerViewContent = binding.rvFeedcontent
        layoutManagerContent = GridLayoutManager(context, 2)
//        menuProfileAdapter = MenuProfileAdapter(temp, onDetailClickListener = {
//            startActivity(Intent(this.context, MenuFeedOpened::class.java))
//        })
//        recyclerViewContent.adapter = menuProfileAdapter
//        recyclerViewContent.layoutManager = layoutManagerContent

        val rvfeedcontent: RecyclerView = requireView().findViewById(R.id.rv_feedcontent)
        val fadeUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_up)
        rvfeedcontent.startAnimation(fadeUpAnimation)
        binding.overlay.setOnClickListener {
            findNavController().navigate(R.id.action_menuFeeds2_to_menuFeedsSearch2)
        }
    }
}