package id.ac.istts.myfit.MenuFeed

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Preferences.CustomMenuPreferences
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.tempUser
import id.ac.istts.myfit.MenuCustom.CustomIngredientsViewModel
import id.ac.istts.myfit.ProfileSetting.MenuProfileSettingV2ViewModel
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuFeedsSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuFeedsSearch : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentMenuFeedsSearchBinding
    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var menuFeedSearchAdapter: MenuFeedSearchAdapter
    private lateinit var layoutManagerSearch: RecyclerView.LayoutManager
    private lateinit var userPreference: UserPreference
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: MenuFeedsSearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuFeedsSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this).get(MenuFeedsSearchViewModel::class.java)
        userPreference = UserPreference(requireContext())

        var tempSearchUser:List<tempUser> = arrayListOf()
        val rvfeedcontent: RecyclerView = requireView().findViewById(R.id.menuFeedsSearch_rvSearch)
        recyclerViewSearch = binding.menuFeedsSearchRvSearch
        layoutManagerSearch = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        menuFeedSearchAdapter = MenuFeedSearchAdapter(tempSearchUser, onDetailClickListener = {
            // TODO: Open profile
        })

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        rvfeedcontent.startAnimation(fadeInAnimation)

        recyclerViewSearch.adapter = menuFeedSearchAdapter
        recyclerViewSearch.layoutManager = layoutManagerSearch

        binding.svSearch.requestFocus()

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                Toast.makeText(requireContext(), newText, Toast.LENGTH_SHORT).show()
                if(binding.svSearch.query.toString()!=""){
                    ioScope.launch {
                        tempSearchUser = vm.search(userPreference.getUser().id.toString(), binding.svSearch.query.toString())
                        mainScope.launch {
                            menuFeedSearchAdapter = MenuFeedSearchAdapter(tempSearchUser, onDetailClickListener = {

                            })
                            binding.menuFeedsSearchRvSearch.adapter = menuFeedSearchAdapter
                        }
                    }
                }else{
                    tempSearchUser = arrayListOf()
                    menuFeedSearchAdapter = MenuFeedSearchAdapter(tempSearchUser, onDetailClickListener = {

                    })
                    binding.menuFeedsSearchRvSearch.adapter = menuFeedSearchAdapter
                }
                return true
            }
        })
        binding.menuFeedsSearchRvSearch.apply {
            this.layoutManager = layoutManager
            this.adapter = menuFeedSearchAdapter
        }
    }
}