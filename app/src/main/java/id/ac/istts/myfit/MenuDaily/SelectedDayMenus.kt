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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuFeed.MenuFeedOpened
import id.ac.istts.myfit.ProfileSetting.MenuProfileAdapter
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentSelectedDayMenusBinding

class SelectedDayMenus : Fragment() {
    private lateinit var binding: FragmentSelectedDayMenusBinding
    private lateinit var recyclerViewContent: RecyclerView
    private lateinit var selectedDayMenusAdapter: SelectedDayMenusAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var image_code = 1001
    private lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_day_menus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectedDayMenusBinding.bind(view)

        userPreference = UserPreference(requireContext())
        var temp:ArrayList<String> = arrayListOf("test", "test2", "test3","test", "test2")

        binding.selectedDayMenuIbBack.setOnClickListener {
            findNavController().navigate(R.id.action_global_menuDaily)
        }

        if(userPreference.getUser().image!=""){
            binding.selectedDayMenuIvProfile.setImageBitmap(decodeBase64ToBitmap(userPreference.getUser().image.toString()))
        }

        recyclerViewContent = binding.selectedDayMenuRvMenu
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        selectedDayMenusAdapter = SelectedDayMenusAdapter(temp)
        recyclerViewContent.adapter = selectedDayMenusAdapter
        recyclerViewContent.layoutManager = layoutManager
    }

    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}