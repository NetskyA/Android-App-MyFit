package id.ac.istts.myfit.MenuFeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.tempUser
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuFeedsSearchUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MenuFeedsSearch_User : Fragment() {

    private lateinit var binding: FragmentMenuFeedsSearchUserBinding

    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var menuFeedSearchUserAdapter: MenuFeedSearchUserAdapter
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
        binding = FragmentMenuFeedsSearchUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(this).get(MenuFeedsSearchViewModel::class.java)
        userPreference = UserPreference(requireContext())

        var tempSearchUser:List<tempUser> = arrayListOf()
        val rvfeedcontent: RecyclerView = requireView().findViewById(R.id.menuFeedsSearchUser_rvUser)
        recyclerViewSearch = binding.menuFeedsSearchUserRvUser
        layoutManagerSearch = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        menuFeedSearchUserAdapter = MenuFeedSearchUserAdapter(tempSearchUser, onDetailClickListener = {
            // TODO: Open profile
        })

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        rvfeedcontent.startAnimation(fadeInAnimation)

        recyclerViewSearch.adapter = menuFeedSearchUserAdapter
        recyclerViewSearch.layoutManager = layoutManagerSearch

        binding.menuFeedsSearchUserRvUser.apply {
            this.layoutManager = layoutManager
            this.adapter = menuFeedSearchUserAdapter
        }
    }

}