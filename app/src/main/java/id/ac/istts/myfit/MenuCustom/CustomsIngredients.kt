package id.ac.istts.myfit.MenuCustom

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import id.ac.istts.myfit.MenuCustom.MenuCustoms
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentCustomsIngredientsBinding

class CustomsIngredients : Fragment() {
    private lateinit var binding: FragmentCustomsIngredientsBinding

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

//        binding.etNamaMenuCustoms.isChanged{
//            Toast.makeText(requireContext(), "Test", Toast.LENGTH_SHORT).show()
//        }

        binding.nextCheckmenu.setOnClickListener{
            Toast.makeText(requireContext(), "Test", Toast.LENGTH_SHORT).show()
        }
    }


}