package id.ac.istts.myfit.MenuCustom

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import id.ac.istts.myfit.Data.Preferences.CustomMenuPreferences
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuCustom.MenuCustoms
import id.ac.istts.myfit.ProfileSetting.ProfileSettingAccountViewModel
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentCustomsIngredientsBinding
import id.ac.istts.myfit.databinding.FragmentMenuCustomsBinding
import id.ac.istts.myfit.databinding.FragmentProfileSettingAccountBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class CustomsIngredients : Fragment() {
    private lateinit var binding: FragmentCustomsIngredientsBinding
//    lateinit var bindingMenuCustomsBinding: FragmentMenuCustomsBinding
    private lateinit var customMenuPreferences: CustomMenuPreferences
    var image_code = 1001
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: CustomIngredientsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_customs_ingredients, container, false)
        binding = FragmentCustomsIngredientsBinding.inflate(inflater, container, false)
//        bindingMenuCustomsBinding = FragmentMenuCustomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customMenuPreferences = CustomMenuPreferences(requireContext())
//        Log.e("PREFERENCE", userPreference.getUser().toString())
        vm = ViewModelProvider(this).get(CustomIngredientsViewModel::class.java)

        binding.nextCheckmenu.setOnClickListener {
            // Move to Result Fragment
            findNavController().navigate(R.id.action_global_customsResult)

            // Change Active
            val navHostFragment = parentFragment as NavHostFragment
            val menuCustoms = navHostFragment.parentFragment as MenuCustoms
            menuCustoms.binding.btnResultCustoms.setBackgroundResource(R.drawable.backgroundnavigations4)
            menuCustoms.binding.btnAddCustoms.setBackgroundColor(Color.parseColor("#0008C4D4"))
            menuCustoms.binding.btnResultCustoms.setTextColor(resources.getColor(R.color.white))
            menuCustoms.binding.btnAddCustoms.setTextColor(resources.getColor(R.color.blue_main))

        }
        binding.etNamaMenuCustoms.setCompoundDrawablesWithIntrinsicBounds(R.drawable.namamenu, 0, 0, 0)
        binding.etNamabahanbahan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ingredients, 0, 0, 0)
        binding.etNamanutrisi.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nutrisi, 0, 0, 0)
        binding.etCaraMemasak.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cooking, 0, 0, 0)
        binding.etNotemenu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.note, 0, 0, 0)

        if(customMenuPreferences.getCustomMenu().image!=""){
            binding.uploadimagemenucustoms.setImageBitmap(decodeBase64ToBitmap(
                customMenuPreferences.getCustomMenu().image.toString()))
        }
        binding.etNamaMenuCustoms.setText(customMenuPreferences.getCustomMenu().name)
        binding.etNamabahanbahan.setText(customMenuPreferences.getCustomMenu().ingredients)
        binding.etNamanutrisi.setText(customMenuPreferences.getCustomMenu().nutrition)
        binding.etCaraMemasak.setText(customMenuPreferences.getCustomMenu().how_to_make)
        binding.etNotemenu.setText(customMenuPreferences.getCustomMenu().note)

        binding.etNamaMenuCustoms.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ioScope.launch {
                    vm.setMenuName(binding.etNamaMenuCustoms.text.toString())
                }
            }

            override fun afterTextChanged(editable: Editable?) {

            }
        })

        binding.etNamabahanbahan.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ioScope.launch {
                    vm.setMenuIngredients(binding.etNamabahanbahan.text.toString())
                }
            }

            override fun afterTextChanged(editable: Editable?) {

            }
        })

        binding.etNamanutrisi.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ioScope.launch {
                    vm.setMenuNutrition(binding.etNamanutrisi.text.toString())
                }
            }

            override fun afterTextChanged(editable: Editable?) {

            }
        })

        binding.etCaraMemasak.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ioScope.launch {
                    vm.setMenuHowToMake(binding.etCaraMemasak.text.toString())
                }
            }

            override fun afterTextChanged(editable: Editable?) {

            }
        })

        binding.etNotemenu.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ioScope.launch {
                    vm.setMenuNote(binding.etNotemenu.text.toString())
                }
            }

            override fun afterTextChanged(editable: Editable?) {

            }
        })

        binding.uploadimagemenucustoms.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, image_code)
        }

        binding.nextCheckmenu.setOnClickListener{
            if(binding.etNamaMenuCustoms.text.toString()=="")Toast.makeText(requireContext(), "Menu name cannot be empty", Toast.LENGTH_SHORT).show()
            else if(binding.etNamabahanbahan.text.toString()=="")Toast.makeText(requireContext(), "Ingredient cannot be empty", Toast.LENGTH_SHORT).show()
            else if(binding.etNamanutrisi.text.toString()=="")Toast.makeText(requireContext(), "Nutrition cannot be empty", Toast.LENGTH_SHORT).show()
            else if(binding.etCaraMemasak.text.toString()=="")Toast.makeText(requireContext(), "How To Make cannot be empty", Toast.LENGTH_SHORT).show()
            else if(customMenuPreferences.getCustomMenu().image=="")Toast.makeText(requireContext(), "Image cannot be empty", Toast.LENGTH_SHORT).show()

//            val navHostFragment = childFragmentManager.findFragmentById(R.id.CustomsContainer) as NavHostFragment
//            navHostFragment.findNavController().navigate(R.id.action_global_customsResult)

//            // Change Active
//            bindingMenuCustomsBinding.btnResultCustoms.setBackgroundResource(R.drawable.backgroundnavigations4)
//            bindingMenuCustomsBinding.btnAddCustoms.setBackgroundColor(Color.parseColor("#0008C4D4"))
//            bindingMenuCustomsBinding.btnResultCustoms.setTextColor(resources.getColor(R.color.white))
//            bindingMenuCustomsBinding.btnAddCustoms.setTextColor(resources.getColor(R.color.blue_main))
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == image_code && resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri? = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, selectedImage)
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            val byteArray = outputStream.toByteArray()
            val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)
//            binding.uploadimagemenucustoms.setImageBitmap(decodeBase64ToBitmap(base64String))



            ioScope.launch { //access ke API
                val msg = vm.uploadImage(base64String)
                mainScope.launch { //update tampilan
                    if(msg=="Fail"){
                        Toast.makeText(requireContext(), "Failed to upload image", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(), "Upload image success", Toast.LENGTH_SHORT).show()
                        binding.uploadimagemenucustoms.setImageBitmap(decodeBase64ToBitmap(customMenuPreferences.getCustomMenu().image.toString()))
                    }
                }
            }
        }
    }

    //    function buat convert base64string jadi img
    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

}