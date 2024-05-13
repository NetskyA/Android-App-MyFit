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

        binding.svSearch.requestFocus()

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                Toast.makeText(requireContext(), newText, Toast.LENGTH_SHORT).show()
                if(binding.svSearch.query.toString()!=""){
                    ioScope.launch {
//                        tempSearchUser = vm.search(userPreference.getUser().id.toString(), binding.svSearch.query.toString())
//                        mainScope.launch {
//                            menuFeedSearchAdapter = MenuFeedSearchUserAdapter(tempSearchUser, onDetailClickListener = {
//
//                            })
//                            binding.menuFeedsSearchRvSearch.adapter = menuFeedSearchAdapter
//                        }
                    }
                }else{
//                    tempSearchUser = arrayListOf()
//                    menuFeedSearchAdapter = MenuFeedSearchUserAdapter(tempSearchUser, onDetailClickListener = {
//
//                    })
//                    binding.menuFeedsSearchRvSearch.adapter = menuFeedSearchAdapter
                }
                return true
            }
        })

        binding.feedSearchBtnUser.setOnClickListener {
            // Navigate
            val navHostFragment = childFragmentManager.findFragmentById(R.id.feedSearch_fragmentContainer) as NavHostFragment
            navHostFragment.findNavController().navigate(R.id.action_global_menuFeedsSearch_User)

            binding.feedSearchBtnUser.setTextColor(resources.getColor(R.color.black))
            binding.feedSearchBtnMenu.setTextColor(resources.getColor(R.color.icon_color))
        }

        binding.feedSearchBtnMenu.setOnClickListener {
            // Navigate
            val navHostFragment = childFragmentManager.findFragmentById(R.id.feedSearch_fragmentContainer) as NavHostFragment
            navHostFragment.findNavController().navigate(R.id.action_global_menuFeedsSearch_Menu)

            binding.feedSearchBtnUser.setTextColor(resources.getColor(R.color.icon_color))
            binding.feedSearchBtnMenu.setTextColor(resources.getColor(R.color.black))
        }

    }
}