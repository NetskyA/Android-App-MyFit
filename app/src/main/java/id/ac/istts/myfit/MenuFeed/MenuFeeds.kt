package id.ac.istts.myfit.MenuFeed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.RandomMenu
import id.ac.istts.myfit.ProfileSetting.MenuProfileAdapter
import id.ac.istts.myfit.ProfileSetting.MenuProfileViewModel
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuFeedsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MenuFeeds : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentMenuFeedsBinding
    private lateinit var recyclerViewContent: RecyclerView
    private lateinit var menuFeedsAdapter: MenuFeedsAdapter
    private lateinit var layoutManagerContent: RecyclerView.LayoutManager
    var image_code = 1001
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: MenuFeedsViewModel
    private lateinit var userPreference: UserPreference
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
        userPreference = UserPreference(requireContext())
        vm = ViewModelProvider(this).get(MenuFeedsViewModel::class.java)
        // Isi RV sementara
//        vm.clearDataMenus()
        var temp:MutableList<RandomMenu> = mutableListOf()
        recyclerViewContent = binding.rvFeedcontent
        layoutManagerContent = GridLayoutManager(context, 2)
        menuFeedsAdapter = MenuFeedsAdapter(temp, onDetailClickListener = {
            startActivity(Intent(this.context, MenuFeedOpened::class.java))
        })
        recyclerViewContent.adapter = menuFeedsAdapter
        recyclerViewContent.layoutManager = layoutManagerContent
        vm.getAllMenus(userPreference.getUser().id!!.toInt())
        vm.menus.observe(viewLifecycleOwner, Observer { menus ->
            // Update UI with the new list of menus
            menuFeedsAdapter.data = menus
            menuFeedsAdapter.notifyDataSetChanged()
        })

        binding.extendedFab.setOnClickListener{
            vm.getAllMenus(userPreference.getUser().id!!.toInt())
            vm.menus.observe(viewLifecycleOwner, Observer { menus ->
                // Update UI with the new list of menus
                menuFeedsAdapter.data = menus
                menuFeedsAdapter.notifyDataSetChanged()
            })
        }

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