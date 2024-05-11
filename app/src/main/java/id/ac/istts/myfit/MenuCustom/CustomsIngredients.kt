package id.ac.istts.myfit.MenuCustom

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import id.ac.istts.myfit.databinding.FragmentProfileSettingAccountBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomsIngredients : Fragment() {
    private lateinit var binding: FragmentCustomsIngredientsBinding
    private lateinit var customMenuPreferences: CustomMenuPreferences
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

        binding.nextCheckmenu.setOnClickListener{
            Toast.makeText(requireContext(), "Test", Toast.LENGTH_SHORT).show()
        }
    }


}