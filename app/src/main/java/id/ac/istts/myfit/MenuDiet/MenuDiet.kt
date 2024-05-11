package id.ac.istts.myfit.MenuDiet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuFeed.MenuFeedOpened
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuDietBinding

class MenuDiet : Fragment() {
    private lateinit var binding: FragmentMenuDietBinding
    private lateinit var userPreference: UserPreference
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
        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        imageView.startAnimation(fadeInAnimation)
        val fadeInAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        textWe.startAnimation(fadeInAnimation2)

        userPreference = UserPreference(requireContext())
        binding.tvTitleHelo.setText("Hello, ${userPreference.getUser().username}")
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

        val temp: ArrayList<String> = arrayListOf("test", "test2", "test3", "test4")
        setupRecyclerView(binding.recyclerViewmenuResultDiet, temp, LinearLayoutManager.HORIZONTAL)
        setupRecyclerView(binding.recyclerViewmenuResultDiet2, temp, LinearLayoutManager.VERTICAL)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, data: ArrayList<String>, orientation: Int) {
        val layoutManager = LinearLayoutManager(context, orientation, false)
        val adapter = if (orientation == LinearLayoutManager.HORIZONTAL) {
            MenuDietAdapter(data) { startActivity(Intent(context, MenuFeedOpened::class.java)) }
        } else {
            MenuDietSearchAdapter(data) { startActivity(Intent(context, MenuFeedOpened::class.java)) }
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
