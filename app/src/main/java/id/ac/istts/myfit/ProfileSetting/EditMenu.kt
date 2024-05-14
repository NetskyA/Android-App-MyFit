package id.ac.istts.myfit.ProfileSetting

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.ActivityEditMenuBinding

class EditMenu : AppCompatActivity() {

    private lateinit var binding: ActivityEditMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEditMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editMenuIbBack.setOnClickListener {
            finish()
        }

        binding.editMenuIvEdit.setOnClickListener {
            binding.editMenuTvAccount.text = "Edit Menu"
            binding.editMenuTvNutrition.isEnabled = true
            binding.editMenuTvIngredients.isEnabled = true
            binding.editMenuTvHowToMake.isEnabled = true
            binding.editMenuTvNotes.isEnabled = true
            binding.editMenuBtnSave.visibility = View.VISIBLE
            binding.editMenuBtnDelete.visibility = View.VISIBLE
        }

        binding.editMenuBtnSave.setOnClickListener {
            binding.editMenuTvAccount.text = "Menu"
            binding.editMenuTvNutrition.isEnabled = false
            binding.editMenuTvIngredients.isEnabled = false
            binding.editMenuTvHowToMake.isEnabled = false
            binding.editMenuTvNotes.isEnabled = false
            binding.editMenuBtnSave.visibility = View.GONE
            binding.editMenuBtnDelete.visibility = View.GONE
        }

        binding.editMenuBtnDelete.setOnClickListener {
            // TODO Modal Delete Confirmation
        }
    }
}