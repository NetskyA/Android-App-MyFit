package id.ac.istts.myfit.ProfileSetting

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

        val menuId = intent.getStringExtra("menuId")
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
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setTitle("Delete Confirmation Prompt")
                .setMessage("Are you sure you want to delete this menu?")
                .setMessage("Please re-enter your post title to confirm deletion.")
                .setView(layoutInflater.inflate(R.layout.dialog_delete_menu, null))
                // Add action buttons.
                .setPositiveButton("Delete",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this, "Menu Deleted", Toast.LENGTH_SHORT).show()
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}