package id.ac.istts.myfit.MenuCustom

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import id.ac.istts.myfit.Data.Preferences.CustomMenuPreferences
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentCustomsResultBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CustomsResult : Fragment() {
    private lateinit var binding: FragmentCustomsResultBinding
    private lateinit var customMenuPreferences: CustomMenuPreferences
    private lateinit var userPreference: UserPreference
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: CustomResultViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomsResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customMenuPreferences = CustomMenuPreferences(requireContext())
        userPreference = UserPreference(requireContext())
//        Log.e("PREFERENCE", userPreference.getUser().toString())
        vm = ViewModelProvider(this).get(CustomResultViewModel::class.java)
        if(customMenuPreferences.getCustomMenu().image!=""){
            binding.uploadimagemenucustoms.setImageBitmap(decodeBase64ToBitmap(customMenuPreferences.getCustomMenu().image.toString()))
        }
        binding.customResultTvUser.setText("${userPreference.getUser().name}")
        binding.customResultTvMenuName.setText(customMenuPreferences.getCustomMenu().name)
        binding.customResultTvNutrition.setText(customMenuPreferences.getCustomMenu().nutrition)
        binding.customResultTvIngredients.setText(customMenuPreferences.getCustomMenu().ingredients)
        binding.customResultTvHowToMake.setText(customMenuPreferences.getCustomMenu().how_to_make)
        binding.customResultTvNotes.setText(customMenuPreferences.getCustomMenu().note)

        binding.nextSubmitMenuCustoms.setOnClickListener{
            if(customMenuPreferences.getCustomMenu().name=="") Toast.makeText(requireContext(), "Menu name cannot be empty", Toast.LENGTH_SHORT).show()
            else if(customMenuPreferences.getCustomMenu().ingredients=="") Toast.makeText(requireContext(), "Ingredient cannot be empty", Toast.LENGTH_SHORT).show()
            else if(customMenuPreferences.getCustomMenu().nutrition=="") Toast.makeText(requireContext(), "Nutrition cannot be empty", Toast.LENGTH_SHORT).show()
            else if(customMenuPreferences.getCustomMenu().how_to_make=="") Toast.makeText(requireContext(), "How To Make cannot be empty", Toast.LENGTH_SHORT).show()
            else if(customMenuPreferences.getCustomMenu().image=="") Toast.makeText(requireContext(), "Image cannot be empty", Toast.LENGTH_SHORT).show()
            else{
                ioScope.launch {
                    val msg = vm.upload()
                    if(msg=="Fail"){
                        mainScope.launch {
                            Toast.makeText(requireContext(), "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        vm.clear()
//                        vm.getAllMenuUser(userPreference.getUser().id!!.toInt())
                        mainScope.launch {
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_global_customsIngredients)

                            val navHostFragment = parentFragment as NavHostFragment
                            val menuCustoms = navHostFragment.parentFragment as MenuCustoms
                            menuCustoms.binding.btnAddCustoms.setBackgroundResource(R.drawable.backgroundnavigations3)
                            menuCustoms.binding.btnResultCustoms.setBackgroundColor(Color.parseColor("#0008C4D4"))
                            menuCustoms.binding.btnAddCustoms.setTextColor(resources.getColor(R.color.white))
                            menuCustoms.binding.btnResultCustoms.setTextColor(resources.getColor(R.color.green_main))
                        }
                    }

//                    mainScope.launch {
//                        if(msg == "Fail"){
//                        }else{
//                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
//                            ioScope.launch {
//                                vm.clear()
//                                mainScope.launch {
//                                    findNavController().navigate(R.id.action_global_customsIngredients)
//
//                                    // Change Active
//                                    val navHostFragment = parentFragment as NavHostFragment
//                                    val menuCustoms = navHostFragment.parentFragment as MenuCustoms
//                                    menuCustoms.binding.btnAddCustoms.setBackgroundResource(R.drawable.backgroundnavigations3)
//                                    menuCustoms.binding.btnResultCustoms.setBackgroundColor(Color.parseColor("#0008C4D4"))
//                                    menuCustoms.binding.btnAddCustoms.setTextColor(resources.getColor(R.color.white))
//                                    menuCustoms.binding.btnResultCustoms.setTextColor(resources.getColor(R.color.green_main))
//                                }
//                            }
//                        }
//                    }
                }
            }
        }
    }
    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}