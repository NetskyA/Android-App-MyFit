package id.ac.istts.myfit.ProfileSetting

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.ActivityEditMenuBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class EditMenu : AppCompatActivity() {

    private lateinit var binding: ActivityEditMenuBinding
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: EditMenuViewModel
    private lateinit var userPreference: UserPreference
    var image_code = 1001
    var hideState: Boolean = false;
    var image = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEditMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference(this)
        vm = ViewModelProvider(this).get(EditMenuViewModel::class.java)

        binding.editMenuIvMenu.isEnabled = false
        val menuId = intent.getStringExtra("menuId")
        var menu = Menu()
        ioScope.launch {
            menu = vm.getOneMenuById(menuId.toString())
            if(menu.id==null || menu.id==0){
                mainScope.launch {
                    binding.editMenuTvLike.setText("")
                    binding.editMenuTvCreatedUsername.setText("")
                    binding.editMenuTvNamaMenu.setText("")
                    binding.editMenuTvNutrition.setText("")
                    binding.editMenuTvIngredients.setText("")
                    binding.editMenuTvHowToMake.setText("")
                    binding.editMenuTvNotes.setText("")
                    binding.editMenuTvSaveContentFeed.setText("")
                    if(menu.image!=""){
                        binding.editMenuIvMenu.setImageBitmap(decodeBase64ToBitmap(menu.image.toString()))
                    }
                    Toast.makeText(this@EditMenu, "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
                }
            }else{
                image = menu.image.toString()
                mainScope.launch {
                    binding.editMenuTvLike.setText(menu.like.toString())
                    binding.editMenuTvCreatedUsername.setText(userPreference.getUser().username)
                    binding.editMenuTvNamaMenu.setText(menu.name)
                    binding.editMenuTvNutrition.setText(menu.nutrition)
                    binding.editMenuTvIngredients.setText(menu.ingredients)
                    binding.editMenuTvHowToMake.setText(menu.how_to_make)
                    binding.editMenuTvNotes.setText(menu.note)
                    binding.editMenuTvSaveContentFeed.setText(menu.date)
                    if(menu.image!=""){
                        binding.editMenuIvMenu.setImageBitmap(decodeBase64ToBitmap(menu.image.toString()))
                    }
                }
            }
        }

        binding.editMenuIbBack.setOnClickListener {
            finish()
        }

        binding.editMenuIvMenu.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, image_code)
        }

        binding.editMenuIvEdit.setOnClickListener {
            binding.editMenuTvAccount.text = "Edit Menu"
            binding.editMenuTvNutrition.isEnabled = true
            binding.editMenuTvIngredients.isEnabled = true
            binding.editMenuTvHowToMake.isEnabled = true
            binding.editMenuTvNotes.isEnabled = true
            binding.editMenuIvMenu.isEnabled = true
            binding.editMenuBtnSave.visibility = View.VISIBLE
            binding.editMenuBtnDelete.visibility = View.VISIBLE
        }

        binding.editMenuBtnSave.setOnClickListener {
            binding.editMenuTvAccount.text = "Menu"
            binding.editMenuTvNutrition.isEnabled = false
            binding.editMenuTvIngredients.isEnabled = false
            binding.editMenuTvHowToMake.isEnabled = false
            binding.editMenuTvNotes.isEnabled = false
            binding.editMenuIvMenu.isEnabled = false
            binding.editMenuBtnSave.visibility = View.GONE
            binding.editMenuBtnDelete.visibility = View.GONE
            var name = binding.editMenuTvNamaMenu.text.toString()
            var nutrition = binding.editMenuTvNutrition.text.toString()
            var ingredients = binding.editMenuTvIngredients.text.toString()
            var howToMake = binding.editMenuTvHowToMake.text.toString()
            var notes = binding.editMenuTvNotes.text.toString()

            if(nutrition=="" || ingredients=="" || howToMake==""){
                Toast.makeText(this, "Nutrition, Ingredients, and How to Make cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                ioScope.launch {
                    var result = vm.editMenuById(menuId.toString(), name, nutrition, ingredients, howToMake, notes, image)
                    mainScope.launch {
                        if(result=="Success"){
                            Toast.makeText(this@EditMenu, "Edited Success!", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this@EditMenu, "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
                            binding.editMenuTvLike.setText(menu.like.toString())
                            binding.editMenuTvCreatedUsername.setText(userPreference.getUser().username)
                            binding.editMenuTvNamaMenu.setText(menu.name)
                            binding.editMenuTvNutrition.setText(menu.nutrition)
                            binding.editMenuTvIngredients.setText(menu.ingredients)
                            binding.editMenuTvHowToMake.setText(menu.how_to_make)
                            binding.editMenuTvNotes.setText(menu.note)
                            binding.editMenuTvSaveContentFeed.setText(menu.date)
                            if(menu.image!=""){
                                binding.editMenuIvMenu.setImageBitmap(decodeBase64ToBitmap(menu.image.toString()))
                            }
                        }
                    }
                }
            }
        }

        binding.editMenuIbHide.setOnClickListener {
            if (hideState) {
                binding.editMenuLayoutDetail.visibility = View.VISIBLE
                binding.editMenuIbHide.setImageResource(R.drawable.hide)
            } else {
                binding.editMenuLayoutDetail.visibility = View.GONE
                binding.editMenuIbHide.setImageResource(R.drawable.hide_invert)
            }
            hideState = !hideState
        }

        binding.editMenuBtnDelete.setOnClickListener {
            // TODO Modal Delete Confirmation
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setTitle("Delete this Menu?")
//                .setMessage("Please re-enter your post title to confirm deletion.")
//                .setView(layoutInflater.inflate(R.layout.dialog_delete_menu, null))
                // Add action buttons.
                .setPositiveButton("Delete",
                    DialogInterface.OnClickListener { dialog, id ->
                        ioScope.launch {
                            var result = vm.deleteMenuById(menu)
                            if(result=="Success"){
                                mainScope.launch {
                                    Toast.makeText(this@EditMenu, "Menu Deleted", Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                            }else{
                                mainScope.launch {
                                    Toast.makeText(this@EditMenu, "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            val dialog: AlertDialog = builder.create()
            dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.dialog_rounded))
            dialog.show()

//            ioScope.launch {
//                var result = vm.deleteMenuById(menu)
//                if(result=="Success"){
//                    mainScope.launch {
//                        Toast.makeText(this@EditMenu, "Menu Deleted", Toast.LENGTH_SHORT).show()
//                        finish()
//                    }
//                }else{
//                    mainScope.launch {
//                        Toast.makeText(this@EditMenu, "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == image_code && resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri? = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            val byteArray = outputStream.toByteArray()
            var base64String: String = ""
            val maxFileSize = 3 * 1024 * 1024 // 3MB in bytes

            if (byteArray.size > maxFileSize) {
                // Display a message or handle the error if the file is too large
                Toast.makeText(this, "Image size Max 3MB", Toast.LENGTH_LONG).show()
                return
            } else {
                // Continue with your processing, such as encoding to Base64
                base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)
                image = base64String
                binding.editMenuIvMenu.setImageBitmap(decodeBase64ToBitmap(image))
                // Update UI or further processing
                // binding.uploadimagemenucustoms.setImageBitmap(decodeBase64ToBitmap(base64String))
            }

        }
    }

    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}