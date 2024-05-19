package id.ac.istts.myfit.MenuDiet

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.MenuDietData
import id.ac.istts.myfit.Data.MenuSearch
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuFeed.MenuFeedOpened
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuDietBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuDiet : Fragment() {
    private lateinit var binding: FragmentMenuDietBinding
    private lateinit var userPreference: UserPreference
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    lateinit var adapter: MenuDietAdapter
    lateinit var adapter2: MenuDietSearchAdapter
    lateinit var vm: MenuDietViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuDietBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView: ImageView = requireView().findViewById(R.id.menudietnih)
        val textWe: TextView = requireView().findViewById(R.id.tvTitleWe)
        val btnReady: Button = requireView().findViewById(R.id.menuDiet_readyBtn)
        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_right)
        imageView.startAnimation(fadeInAnimation)
        val fadeInAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_left)
        textWe.startAnimation(fadeInAnimation2)
        val fadeInAnimation3 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_up)
        btnReady.startAnimation(fadeInAnimation3)
        var temp:MutableList<MenuDietData> = mutableListOf()
        var temp2:MutableList<MenuSearch> = mutableListOf()
        vm = ViewModelProvider(this).get(MenuDietViewModel::class.java)
        ioScope.launch {
            vm.getMenuDiet()
        }
        userPreference = UserPreference(requireContext())
        binding.tvTitleHelo.setText("Hello, ${userPreference.getUser().username}")

        val searchView = binding.searchView

        val searchTextId = searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val searchText = searchView.findViewById<TextView>(searchTextId)
        searchText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white)) // Warna hijau
        searchText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.white)) // Warna abu-abu

        val searchIconId = searchView.context.resources.getIdentifier("android:id/search_mag_icon", null, null)
        val searchIcon = searchView.findViewById<ImageView>(searchIconId)
        searchIcon.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), PorterDuff.Mode.SRC_IN)


        // Spinner Day
        val spinner = binding.menuDietSpDay
        spinner.setSelection(0, false)
        spinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item_diet, resources.getStringArray(R.array.day))
        /*spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(requireContext(), "Selected: " + spinner.selectedItem.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }*/


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle the query submission
                ioScope.launch {
                    vm.searchMenu(query!!)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle the query text change
                ioScope.launch {
                    vm.searchMenu(newText!!)
                }
                // You can filter your data here based on newText
                return true
            }
        })
        val menuDietLayoutIds = listOf(
            R.id.linearLayoutmenudiet1,
        )

        // Load the animation once
        val fadeInAnimation1 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_up)

        // Apply the same animation to each ConstraintLayout found by ID
        menuDietLayoutIds.forEach { layoutId ->
            val layout = requireView().findViewById<ConstraintLayout>(layoutId)
            layout.startAnimation(fadeInAnimation1)
        }
        adapter = MenuDietAdapter(temp){ menuItem, status ->
            if(!status){
                startActivity(Intent(context, MenuFeedOpened::class.java).apply {
                    putExtra("Menu_ID", menuItem.id.toString())
                })
            }else{
                vm.deleteMenu(menuItem.id!!, binding.menuDietSpDay.selectedItem.toString())
                adapter.notifyDataSetChanged()
            }}
        adapter2 = MenuDietSearchAdapter(temp2,{ data -> startActivity(Intent(context, MenuFeedOpened::class.java).apply {
            putExtra("Menu_ID", data.id.toString())
        }) },{ id -> vm.addMenu(id,binding.menuDietSpDay.selectedItem.toString()) })
        if(temp.isEmpty()){
            binding.recyclerViewmenuResultDiet.visibility = View.GONE
            binding.buttondelete.visibility = View.GONE
        }
        setupRecyclerView(binding.recyclerViewmenuResultDiet, LinearLayoutManager.HORIZONTAL)
        setupRecyclerView(binding.recyclerViewmenuResultDiet2, LinearLayoutManager.VERTICAL)
        binding.buttondelete.setOnClickListener{
            adapter.status = !adapter.status!!
            adapter.notifyDataSetChanged()
        }

        vm.menuSearch.observe(viewLifecycleOwner, Observer { menus ->
            // Update UI with the new list of menus
            adapter2.data = menus
            adapter2.notifyDataSetChanged()
        })
        vm.menu.observe(viewLifecycleOwner, Observer { menus ->
            // Update UI with the new list of menus
            Log.e("MenuEmpty",menus.toString())
            if(menus.isEmpty()){
                binding.recyclerViewmenuResultDiet.visibility = View.GONE
                binding.buttondelete.visibility = View.GONE
            }else{
                binding.recyclerViewmenuResultDiet.visibility = View.VISIBLE
                binding.buttondelete.visibility = View.VISIBLE
            }
            adapter.data = menus
            adapter.notifyDataSetChanged()
        })

        binding.menuDietReadyBtn.setOnClickListener {
            ioScope.launch {
                val hasil = vm.saveMenuDiet()
                requireActivity().runOnUiThread {
                    if (hasil == "Failed") {
                        Toast.makeText(context, "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
                    }else{
                        adapter2.data.clear()
                        adapter2.notifyDataSetChanged()
                        Toast.makeText(context, "Menu Diet Saved", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Handle the selected item
                vm.setMenu(selectedItem)
                // You can also start an activity or update the UI based on the selected item
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, orientation: Int) {
        val layoutManager = LinearLayoutManager(context, orientation, false)
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            recyclerView.adapter = adapter
        } else {
            recyclerView.adapter = adapter2
        }
        recyclerView.layoutManager = layoutManager
    }
}
