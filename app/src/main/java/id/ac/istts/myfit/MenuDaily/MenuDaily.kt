package id.ac.istts.myfit.MenuDaily

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.MenuDietData
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuFeed.MenuFeedOpened
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuDailyBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MenuDaily : Fragment() {

    private lateinit var binding: FragmentMenuDailyBinding
    private lateinit var userPreference: UserPreference
    private lateinit var vm: MenuDailyViewModel
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    lateinit var recyclerViewMonday: RecyclerView
    lateinit var recyclerViewTuesday: RecyclerView
    lateinit var recyclerViewWednesday: RecyclerView
    lateinit var recyclerViewThursday: RecyclerView
    lateinit var recyclerViewFriday: RecyclerView
    lateinit var recyclerViewSaturday: RecyclerView
    lateinit var recyclerViewSunday: RecyclerView

    lateinit var adapterMonday: MenuDailyAdapter
    lateinit var adapterTuesday: MenuDailyAdapter
    lateinit var adapterWednesday: MenuDailyAdapter
    lateinit var adapterThursday: MenuDailyAdapter
    lateinit var adapterFriday: MenuDailyAdapter
    lateinit var adapterSaturday: MenuDailyAdapter
    lateinit var adapterSunday: MenuDailyAdapter

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
        var temp:ArrayList<MenuDietData> = arrayListOf()
        vm = ViewModelProvider(this).get(MenuDailyViewModel::class.java)
        val imageView: ImageView = requireView().findViewById(R.id.imageView7)
        val lymenutoday: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday)
        val lymenutoday2: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday2)
        val lymenutoday3: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday3)
        val lymenutoday4: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday4)
        val lymenutoday5: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday5)
        val lymenutoday6: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday6)
        val lymenutoday7: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday7)
        val textWe: TextView = requireView().findViewById(R.id.tvTitleWe3)
        val clycontentmenutoday: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday)

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_right)
        imageView.startAnimation(fadeInAnimation)

        val fadeInAnimation1 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        clycontentmenutoday.startAnimation(fadeInAnimation1)

        val fadeInAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_left)
        textWe.startAnimation(fadeInAnimation2)

        val fadeInAnimation3 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        lymenutoday.startAnimation(fadeInAnimation3)
        val fadeInAnimation5 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        lymenutoday2.startAnimation(fadeInAnimation5)
        val fadeInAnimation6 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        lymenutoday3.startAnimation(fadeInAnimation6)
        val fadeInAnimation7 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        lymenutoday4.startAnimation(fadeInAnimation7)
        val fadeInAnimation8 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        lymenutoday5.startAnimation(fadeInAnimation8)
        val fadeInAnimation9 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        lymenutoday6.startAnimation(fadeInAnimation9)
        val fadeInAnimation10 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        lymenutoday7.startAnimation(fadeInAnimation10)

        /*val fadeInAnimation3 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        buttonnext.startAnimation(fadeInAnimation3)*/

        val fadeInAnimation4 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)

        // Recycler View
        recyclerViewMonday = binding.menuDailyRvMonday
        recyclerViewTuesday = binding.menuDailyRvTuesday
        recyclerViewWednesday = binding.menuDailyRvWednesday
        recyclerViewThursday = binding.menuDailyRvThursday
        recyclerViewFriday = binding.menuDailyRvFriday
        recyclerViewSaturday = binding.menuDailyRvSaturday
        recyclerViewSunday = binding.menuDailyRvSunday

        adapterMonday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
                putExtra("Menu_ID", it.toString())
            }
            startActivity(intent)
        })
        ioScope.launch {
            vm.getMenuDiet()
        }
        vm.menu.observe(viewLifecycleOwner, Observer { menus ->
            // Update UI with the new list of menus
            adapterMonday.data = vm.setMenu("Monday")
            adapterMonday.notifyDataSetChanged()
            if(adapterMonday.data.size == 0){
                binding.lycontentmenutoday2.visibility = View.GONE
            }
            adapterTuesday.data = vm.setMenu("Tuesday")
            adapterTuesday.notifyDataSetChanged()
            if(adapterTuesday.data.size == 0){
                binding.lycontentmenutoday3.visibility = View.GONE
            }
            adapterWednesday.data = vm.setMenu("Wednesday")
            adapterWednesday.notifyDataSetChanged()
            if(adapterWednesday.data.size == 0){
                binding.lycontentmenutoday4.visibility = View.GONE
            }
            adapterThursday.data = vm.setMenu("Thursday")
            adapterThursday.notifyDataSetChanged()
            if(adapterThursday.data.size == 0){
                binding.lycontentmenutoday5.visibility = View.GONE
            }
            adapterFriday.data = vm.setMenu("Friday")
            adapterFriday.notifyDataSetChanged()
            if(adapterFriday.data.size == 0){
                binding.lycontentmenutoday6.visibility = View.GONE
            }
            adapterSaturday.data = vm.setMenu("Saturday")
            adapterSaturday.notifyDataSetChanged()
            if(adapterSaturday.data.size == 0){
                binding.lycontentmenutoday7.visibility = View.GONE
            }
            adapterSunday.data = vm.setMenu("Sunday")
            adapterSunday.notifyDataSetChanged()
            if(adapterSunday.data.size == 0){
                binding.lycontentmenutoday.visibility = View.GONE
            }
        })
        adapterTuesday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
                putExtra("Menu_ID", it.toString())
            }
            startActivity(intent)
        })
        adapterWednesday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
                putExtra("Menu_ID", it.toString())
            }
            startActivity(intent)
        })
        adapterThursday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
                putExtra("Menu_ID", it.toString())
            }
            startActivity(intent)
        })
        adapterFriday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
                putExtra("Menu_ID", it.toString())
            }
            startActivity(intent)
        })
        adapterSaturday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
                putExtra("Menu_ID", it.toString())
            }
            startActivity(intent)
        })
        adapterSunday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
                putExtra("Menu_ID", it.toString())
            }
            startActivity(intent)
        })

        recyclerViewMonday.adapter = adapterMonday
        recyclerViewMonday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewTuesday.adapter = adapterTuesday
        recyclerViewTuesday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewWednesday.adapter = adapterWednesday
        recyclerViewWednesday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewThursday.adapter = adapterThursday
        recyclerViewThursday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewFriday.adapter = adapterFriday
        recyclerViewFriday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewSaturday.adapter = adapterSaturday
        recyclerViewSaturday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewSunday.adapter = adapterSunday
        recyclerViewSunday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        userPreference = UserPreference(requireContext())
        if(userPreference.getUser().image!=""){
            binding.imageView6.setImageBitmap(decodeBase64ToBitmap(userPreference.getUser().image.toString()))
        }
        binding.tvTitleWe2.setText(userPreference.getUser().username)

        binding.lycontentmenutoday.setOnClickListener {
            selectedDayMenus = adapterSunday.data
            findNavController().navigate(R.id.action_menuDaily_to_selectedDayMenus)
        }
        binding.lycontentmenutoday2.setOnClickListener {
            selectedDayMenus = adapterMonday.data
            findNavController().navigate(R.id.action_menuDaily_to_selectedDayMenus)
        }
        binding.lycontentmenutoday3.setOnClickListener {
            selectedDayMenus = adapterTuesday.data
            findNavController().navigate(R.id.action_menuDaily_to_selectedDayMenus)
        }
        binding.lycontentmenutoday4.setOnClickListener {
            selectedDayMenus = adapterWednesday.data
            findNavController().navigate(R.id.action_menuDaily_to_selectedDayMenus)
        }
        binding.lycontentmenutoday5.setOnClickListener {
            selectedDayMenus = adapterThursday.data
            findNavController().navigate(R.id.action_menuDaily_to_selectedDayMenus)
        }
        binding.lycontentmenutoday6.setOnClickListener {
            selectedDayMenus = adapterFriday.data
            findNavController().navigate(R.id.action_menuDaily_to_selectedDayMenus)
        }
        binding.lycontentmenutoday7.setOnClickListener {
            selectedDayMenus = adapterSaturday.data
            findNavController().navigate(R.id.action_menuDaily_to_selectedDayMenus)
        }
    }
    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    companion object{
        var selectedDayMenus = mutableListOf<MenuDietData>()
    }

}