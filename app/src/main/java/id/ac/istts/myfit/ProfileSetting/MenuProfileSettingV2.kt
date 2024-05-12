package id.ac.istts.myfit.ProfileSetting

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.Data.Modal.ModalDeleteAccount
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MyFitApplication
import id.ac.istts.myfit.R
import id.ac.istts.myfit.SignAll.MenuSigninAll
import id.ac.istts.myfit.databinding.ActivityMenuProfileSettingV2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class MenuProfileSettingV2 : AppCompatActivity() {

    lateinit var binding: ActivityMenuProfileSettingV2Binding
    private lateinit var userPreference: UserPreference
    var image_code = 1001
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: MenuProfileSettingV2ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMenuProfileSettingV2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(MenuProfileSettingV2ViewModel::class.java)
        userPreference = UserPreference(this)

        if(userPreference.getUser().image!=""){
            binding.ivUserprofiles.setImageBitmap(decodeBase64ToBitmap(userPreference.getUser().image.toString()))
        }

//        Log.e("PREFERENCEPROFIL", userPreference.getUser().toString())
        binding.tvProfile.setText(userPreference.getUser().username)

        binding.button3.setOnClickListener{
            userPreference.clearPref()
            startActivity(Intent(this,MenuSigninAll::class.java))
        }
        binding.logoutFromProfileSetting.setOnClickListener {
            userPreference.clearPref()
            startActivity(Intent(this,MenuSigninAll::class.java))
        }

        binding.llPersonal.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingAccount)
        }

        binding.llBody.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingBody)
        }

        binding.ibAccount.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingAccount)
        }

        binding.ibBody.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingBody)
        }

        binding.llSecurity.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingPassword)
        }

        binding.ibSecurity.setOnClickListener {
            findNavController(R.id.fragmentContainerView2).navigate(R.id.action_global_profileSettingPassword)
        }

        binding.mpBtnBack.setOnClickListener {
            finish()
        }

        binding.ivUserprofiles.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, image_code)
        }

        val linearLayout22 = binding.fragmentContainerView2

        // Load and apply the animation
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_up)
        linearLayout22.startAnimation(fadeInAnimation)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == image_code && resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri? = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            val byteArray = outputStream.toByteArray()
            val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)
//            binding.ivUserprofiles.setImageBitmap(decodeBase64ToBitmap(base64String))



            ioScope.launch { //access ke API
                val msg = vm.uploadImage(base64String)
                mainScope.launch { //update tampilan
                    if(msg=="Fail"){
                        Toast.makeText(this@MenuProfileSettingV2, "Failed to upload image", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@MenuProfileSettingV2, "Upload image success", Toast.LENGTH_SHORT).show()
                        binding.ivUserprofiles.setImageBitmap(decodeBase64ToBitmap(userPreference.getUser().image.toString()))
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