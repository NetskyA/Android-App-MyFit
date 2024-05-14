package id.ac.istts.myfit.ProfileSetting

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.AllMenuUser
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuFeed.MenuFeedOpened
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuProfile : Fragment() {
    private lateinit var binding: FragmentMenuProfileBinding
    private lateinit var recyclerViewContent: RecyclerView
    private lateinit var menuProfileAdapter: MenuProfileAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var image_code = 1001
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: MenuProfileViewModel
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreference = UserPreference(requireContext())
        vm = ViewModelProvider(this).get(MenuProfileViewModel::class.java)

        var temp:ArrayList<Menu> = arrayListOf()

        recyclerViewContent = binding.rvFeedcontent
        layoutManager = GridLayoutManager(context, 2)
        menuProfileAdapter = MenuProfileAdapter(temp, onDetailClickListener = {
            startActivity(Intent(this.context, MenuFeedOpened::class.java))
        })
        recyclerViewContent.adapter = menuProfileAdapter
        recyclerViewContent.layoutManager = layoutManager
        vm.getAllMenuUser(userPreference.getUser().id!!.toInt())
        vm.menus.observe(viewLifecycleOwner, Observer { menus ->
            // Update UI with the new list of menus
            menuProfileAdapter.data = menus
            menuProfileAdapter.notifyDataSetChanged()
            binding.tvCountcreated.setText("${menus.size} Posts")
            binding.tvFavorite.setText("${vm.countFavorite(menus)} Favorites")

        })

//        val userId = userPreference.getUser().id // Replace with actual user ID
//        vm.getAllMenuUser(userId!!.toInt())

        binding.btnbydate.setOnClickListener {
            if(binding.btnbydate.text=="Recent"){
                binding.btnbydate.setText("Reset")
                binding.filterbykategori.setText(("Favorite"))
                vm.sortRecent()
                vm.menus.observe(viewLifecycleOwner, Observer { menus ->
                    // Update UI with the new list of menus
                    temp = menus
                    menuProfileAdapter = MenuProfileAdapter(temp, onDetailClickListener = {
                        startActivity(Intent(this.context, MenuFeedOpened::class.java))
                    })
                    recyclerViewContent.adapter = menuProfileAdapter
                    recyclerViewContent.layoutManager = layoutManager
                })
            }else{
                binding.btnbydate.setText("Recent")
                vm.reset()
                vm.menus.observe(viewLifecycleOwner, Observer { menus ->
                    // Update UI with the new list of menus
                    temp = menus
                    menuProfileAdapter = MenuProfileAdapter(temp, onDetailClickListener = {
                        startActivity(Intent(this.context, MenuFeedOpened::class.java))
                    })
                    recyclerViewContent.adapter = menuProfileAdapter
                    recyclerViewContent.layoutManager = layoutManager
                })
            }
        }

        binding.filterbykategori.setOnClickListener {
            if(binding.filterbykategori.text=="Favorite"){
                binding.filterbykategori.setText("Reset")
                binding.btnbydate.setText(("Recent"))
                vm.sortFavorite()
                vm.menus.observe(viewLifecycleOwner, Observer { menus ->
                    // Update UI with the new list of menus
                    temp = menus
                    menuProfileAdapter = MenuProfileAdapter(temp, onDetailClickListener = {
                        startActivity(Intent(this.context, MenuFeedOpened::class.java))
                    })
                    recyclerViewContent.adapter = menuProfileAdapter
                    recyclerViewContent.layoutManager = layoutManager
                })
            }else{
                binding.filterbykategori.setText("Favorite")
                vm.reset()
                vm.menus.observe(viewLifecycleOwner, Observer { menus ->
                    // Update UI with the new list of menus
                    temp = menus
                    menuProfileAdapter = MenuProfileAdapter(temp, onDetailClickListener = {
                        startActivity(Intent(this.context, MenuFeedOpened::class.java))
                    })
                    recyclerViewContent.adapter = menuProfileAdapter
                    recyclerViewContent.layoutManager = layoutManager
                })
            }
        }

        val rv_feedcontent: RecyclerView = requireView().findViewById(R.id.rv_feedcontent)

//        Log.e("PREFERENCE", userPreference.getUser().toString())

        binding.tvProfile.setText(userPreference.getUser().username)

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_up)
        rv_feedcontent.startAnimation(fadeInAnimation)

        if(userPreference.getUser().image!=""){
            binding.ivUserprofiles.setImageBitmap(decodeBase64ToBitmap(userPreference.getUser().image.toString()))
        }

        binding.lybar2.setOnClickListener {
//            val stepmove = MenuProfileDirections.actionMenuProfileToMenuProfileSeeting()
//            findNavController().navigate(stepmove)
            startActivity(Intent(this.context, MenuProfileSettingV2::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        if(userPreference.getUser().image!=""){
            binding.ivUserprofiles.setImageBitmap(decodeBase64ToBitmap(userPreference.getUser().image.toString()))
        }
    }

    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}