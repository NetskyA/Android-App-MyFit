package id.ac.istts.myfit.MenuDaily

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuDiet.MenuDietAdapter
import id.ac.istts.myfit.MenuDiet.MenuDietSearchAdapter
import id.ac.istts.myfit.MenuFeed.MenuFeedOpened
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuDailyBinding


class MenuDaily : Fragment() {

    private lateinit var binding: FragmentMenuDailyBinding
    private lateinit var userPreference: UserPreference

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
        var temp:ArrayList<String> = arrayListOf("test", "test2", "test3","test", "test2")

        val imageView: ImageView = requireView().findViewById(R.id.imageView7)
        val lymenutoday: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday)
        val textWe: TextView = requireView().findViewById(R.id.tvTitleWe3)
        val clycontentmenutoday: LinearLayout = requireView().findViewById(R.id.lycontentmenutoday)

        val fadeInAnimation3 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_up)
        lymenutoday.startAnimation(fadeInAnimation3)

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_right)
        imageView.startAnimation(fadeInAnimation)

        val fadeInAnimation1 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        clycontentmenutoday.startAnimation(fadeInAnimation1)

        val fadeInAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_left)
        textWe.startAnimation(fadeInAnimation2)

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
//                putExtra("Menu_ID", it.id.toString())
            }
            startActivity(intent)
        })
        adapterTuesday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
//                putExtra("Menu_ID", it.id.toString())
            }
            startActivity(intent)
        })
        adapterWednesday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
//                putExtra("Menu_ID", it.id.toString())
            }
            startActivity(intent)
        })
        adapterThursday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
//                putExtra("Menu_ID", it.id.toString())
            }
            startActivity(intent)
        })
        adapterFriday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
//                putExtra("Menu_ID", it.id.toString())
            }
            startActivity(intent)
        })
        adapterSaturday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
//                putExtra("Menu_ID", it.id.toString())
            }
            startActivity(intent)
        })
        adapterSunday = MenuDailyAdapter(temp, onDetailClickListener = {
            val intent = Intent(this.context, MenuFeedOpened::class.java).apply {
//                putExtra("Menu_ID", it.id.toString())
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
            findNavController().navigate(R.id.action_menuDaily_to_selectedDayMenus)
        }

    }
    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

}