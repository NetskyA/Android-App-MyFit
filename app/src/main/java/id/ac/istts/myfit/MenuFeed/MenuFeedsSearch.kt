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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Preferences.CustomMenuPreferences
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.tempUser
import id.ac.istts.myfit.MenuCustom.CustomIngredientsViewModel
import id.ac.istts.myfit.ProfileSetting.MenuProfileAdapter
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
    private lateinit var menuFeedSearchUserAdapter: MenuFeedSearchUserAdapter
    private lateinit var menuFeedSearchMenuAdapter: MenuProfileAdapter
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
        val rvfeedcontent: RecyclerView = requireView().findViewById(R.id.feedSearch_rvContent)
        recyclerViewSearch = binding.feedSearchRvContent
        layoutManagerSearch = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        menuFeedSearchUserAdapter = MenuFeedSearchUserAdapter(tempSearchUser, onDetailClickListener = {
            val action = MenuFeedsSearchDirections.actionMenuFeedsSearch2ToSearchUserProfile(it.username)
            findNavController().navigate(action)
        })
        binding.feedSearchBtnUser.isEnabled = false
        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        rvfeedcontent.startAnimation(fadeInAnimation)

        recyclerViewSearch.adapter = menuFeedSearchUserAdapter
        recyclerViewSearch.layoutManager = layoutManagerSearch

        binding.svSearch.requestFocus()

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                Toast.makeText(requireContext(), newText, Toast.LENGTH_SHORT).show()
                if(binding.feedSearchBtnUser.isEnabled==false){
                    if(binding.svSearch.query.toString()!=""){
                        ioScope.launch {
                            tempSearchUser = vm.search(userPreference.getUser().id.toString(), binding.svSearch.query.toString())
                            mainScope.launch {
                                menuFeedSearchUserAdapter = MenuFeedSearchUserAdapter(tempSearchUser, onDetailClickListener = {
                                    val action = MenuFeedsSearchDirections.actionMenuFeedsSearch2ToSearchUserProfile(it.username)
                                    findNavController().navigate(action)
                                })
                                recyclerViewSearch.adapter = menuFeedSearchUserAdapter
                            }
                        }
                    }else{
                        tempSearchUser = arrayListOf()
                        menuFeedSearchUserAdapter = MenuFeedSearchUserAdapter(tempSearchUser, onDetailClickListener = {
                            val action = MenuFeedsSearchDirections.actionMenuFeedsSearch2ToSearchUserProfile(it.username)
                            findNavController().navigate(action)
                        })
                        recyclerViewSearch.adapter = menuFeedSearchUserAdapter
                    }
                }else{
                    var tempMenu:ArrayList<Menu> = arrayListOf()
                    menuFeedSearchMenuAdapter = MenuProfileAdapter(tempMenu, onDetailClickListener = {
//                val action = MenuFeedsSearchDirections.actionMenuFeedsSearch2ToSearchUserProfile(it.username)
//                findNavController().navigate(action)
                    })
                }

                return true
            }
        })

        binding.feedSearchBtnUser.setOnClickListener {
            binding.feedSearchBtnUser.setTextColor(resources.getColor(R.color.black))
            binding.feedSearchBtnMenu.setTextColor(resources.getColor(R.color.icon_color))
            binding.feedSearchBtnUser.isEnabled = false
            binding.feedSearchBtnMenu.isEnabled = true
            binding.svSearch.setQuery("", true)
            tempSearchUser = arrayListOf()
            menuFeedSearchUserAdapter = MenuFeedSearchUserAdapter(tempSearchUser, onDetailClickListener = {
                val action = MenuFeedsSearchDirections.actionMenuFeedsSearch2ToSearchUserProfile(it.username)
                findNavController().navigate(action)
            })
            recyclerViewSearch.adapter = menuFeedSearchUserAdapter
        }

        binding.feedSearchBtnMenu.setOnClickListener {
            binding.feedSearchBtnUser.setTextColor(resources.getColor(R.color.icon_color))
            binding.feedSearchBtnMenu.setTextColor(resources.getColor(R.color.black))
            binding.feedSearchBtnUser.isEnabled = true
            binding.feedSearchBtnMenu.isEnabled = false
            binding.svSearch.setQuery("", true)
            var tempMenu:ArrayList<Menu> = arrayListOf()
            menuFeedSearchMenuAdapter = MenuProfileAdapter(tempMenu, onDetailClickListener = {
//                val action = MenuFeedsSearchDirections.actionMenuFeedsSearch2ToSearchUserProfile(it.username)
//                findNavController().navigate(action)
            })
            recyclerViewSearch.adapter = menuFeedSearchMenuAdapter
        }

    }
}