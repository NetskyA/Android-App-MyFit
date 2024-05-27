package id.ac.istts.myfit.MenuFeed

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.User
import id.ac.istts.myfit.ProfileSetting.EditMenu
import id.ac.istts.myfit.ProfileSetting.MenuProfileAdapter
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentSearchUserProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchUserProfile : Fragment() {

    private lateinit var binding: FragmentSearchUserProfileBinding
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: SearchUserViewModel
    private lateinit var userPreference: UserPreference
    var userId: Int = 0

    private lateinit var recyclerViewMenu: RecyclerView
    private lateinit var detailUserMenuAdapter: MenuProfileAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreference = UserPreference(requireContext())
        vm = ViewModelProvider(this).get(SearchUserViewModel::class.java)

        recyclerViewMenu = binding.searchUserProfileRvFeedContent

        var username = SearchUserProfileArgs?.fromBundle(arguments as Bundle)!!.username

        var user: User = User()
        var menus: List<Menu> = arrayListOf()

        ioScope.launch {
            user = vm.getUserByUsername(username.toString())
            if(user.id==0 || user.id==null){
                Toast.makeText(requireContext(), "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
            }else{
                userId = user.id!!.toInt()
                menus = vm.getUserMenu(user.id!!.toInt())
                val fav = vm.countFav(menus)
                mainScope.launch {
                    binding.searchUserProfileTvProfile.setText(user.username)
                    if(user.image!=""){
                        binding.searchUserProfileIvUserProfile.setImageBitmap(decodeBase64ToBitmap(user.image.toString()))
                    }
                    binding.searchUserProfileTvFavorite.setText(fav.toString())

                    binding.searchUserProfileTvCountCreated.setText(menus.size.toString())

                    detailUserMenuAdapter = MenuProfileAdapter(ArrayList(menus), onDetailClickListener = {
                        val intent = Intent(requireContext(), MenuFeedOpened::class.java).apply {
                            putExtra("Menu_ID", it.id.toString())
                        }
                        startActivity(intent)
                    })
                    recyclerViewMenu.adapter = detailUserMenuAdapter
                    recyclerViewMenu.layoutManager = GridLayoutManager(context, 2)
                }
            }
        }

        binding.searchUserProfileBtnFilterRecent.setOnClickListener {
            if(binding.searchUserProfileBtnFilterRecent.text=="Oldest"){
                binding.searchUserProfileBtnFilterKategori.setBackgroundResource(R.drawable.borders6)
                binding.searchUserProfileBtnFilterRecent.setBackgroundResource(R.drawable.backgroundnavigations10)
                binding.searchUserProfileBtnFilterRecent.setText("Newest")
                binding.searchUserProfileBtnFilterKategori.setText(("Favorite"))
                ioScope.launch {
                    var tempMenu = vm.sortOldest(menus)
                    mainScope.launch {
                        detailUserMenuAdapter = MenuProfileAdapter(ArrayList(tempMenu), onDetailClickListener = {
                            val intent = Intent(requireContext(), MenuFeedOpened::class.java).apply {
                                putExtra("Menu_ID", it.id.toString())
                            }
                            startActivity(intent)
                        })
                        recyclerViewMenu.adapter = detailUserMenuAdapter
                    }
                }
            }else{
                binding.searchUserProfileBtnFilterRecent.setText("Oldest")
                binding.searchUserProfileBtnFilterKategori.setBackgroundResource(R.drawable.borders6)
                binding.searchUserProfileBtnFilterRecent.setBackgroundResource(R.drawable.borders6)

                detailUserMenuAdapter = MenuProfileAdapter(ArrayList(menus), onDetailClickListener = {
                    val intent = Intent(requireContext(), MenuFeedOpened::class.java).apply {
                        putExtra("Menu_ID", it.id.toString())
                    }
                    startActivity(intent)
                })
                recyclerViewMenu.adapter = detailUserMenuAdapter
            }
        }

        binding.searchUserProfileBtnFilterKategori.setOnClickListener {
            if(binding.searchUserProfileBtnFilterKategori.text=="Favorite"){
                binding.searchUserProfileBtnFilterKategori.setBackgroundResource(R.drawable.backgroundnavigations10)
                binding.searchUserProfileBtnFilterRecent.setBackgroundResource(R.drawable.borders6)
                binding.searchUserProfileBtnFilterKategori.setText("Reset")
                binding.searchUserProfileBtnFilterRecent.setText(("Oldest"))
                ioScope.launch {
                    var tempMenu = vm.sortFav(menus)
                    mainScope.launch {
                        detailUserMenuAdapter = MenuProfileAdapter(ArrayList(tempMenu), onDetailClickListener = {
                            val intent = Intent(requireContext(), MenuFeedOpened::class.java).apply {
                                putExtra("Menu_ID", it.id.toString())
                            }
                            startActivity(intent)
                        })
                        recyclerViewMenu.adapter = detailUserMenuAdapter
                    }
                }
            }else{
                binding.searchUserProfileBtnFilterKategori.setText("Favorite")
                binding.searchUserProfileBtnFilterKategori.setBackgroundResource(R.drawable.borders6)
                binding.searchUserProfileBtnFilterRecent.setBackgroundResource(R.drawable.borders6)
                detailUserMenuAdapter = MenuProfileAdapter(ArrayList(menus), onDetailClickListener = {
                    val intent = Intent(requireContext(), MenuFeedOpened::class.java).apply {
                        putExtra("Menu_ID", it.id.toString())
                    }
                    startActivity(intent)
                })
                recyclerViewMenu.adapter = detailUserMenuAdapter
            }
        }

    }
    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    override fun onResume() {
        super.onResume()
        if(userId!=0){
            ioScope.launch {
                var tempMenu = vm.getUserMenu(userId)
                var tempFav = vm.countFav(tempMenu)
                mainScope.launch {
                    binding.searchUserProfileTvFavorite.setText(tempFav.toString())
                }
            }
        }
    }
}