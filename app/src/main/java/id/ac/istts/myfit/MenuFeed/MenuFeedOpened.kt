package id.ac.istts.myfit.MenuFeed

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.ActivityMenuFeedOpenedBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuFeedOpened : AppCompatActivity() {

    private lateinit var binding: ActivityMenuFeedOpenedBinding
    var image_code = 1001
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var vm: MenuFeedOpenedViewModel
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_menu_feed_opened)

        userPreference = UserPreference(this)
        vm = ViewModelProvider(this).get(MenuFeedOpenedViewModel::class.java)
        binding = ActivityMenuFeedOpenedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuId = intent.getStringExtra("Menu_ID")
//        Log.d("MenuID", menuId.toString())
        binding.profileMFO.setImageBitmap(decodeBase64ToBitmap(userPreference.getUser().image.toString()))
        var tempMenu = Menu()
        var username = ""
        ioScope.launch {
            tempMenu = vm.getOneMenuById(menuId.toString())
            username = vm.getUsername(tempMenu.user_id.toString())
            var cekLike = vm.getLikeMenu(tempMenu.id.toString(), userPreference.getUser().id.toString())
            mainScope.launch {
                binding.tvTitleMFO.setText(tempMenu.name)
                binding.tvLikeFMO.setText(tempMenu.like.toString()+" Favorite")
                binding.tvUsernameMFO.setText(username)
                binding.MenuFeedTvNutrition.setText(tempMenu.nutrition)
                binding.MenuFeedTvIngredients.setText(tempMenu.ingredients)
                binding.MenuFeedTvHowToMake.setText(tempMenu.how_to_make)
                binding.MenuFeedTvNotes.setText(tempMenu.note)
                binding.uploadcontentfeed.setText(tempMenu.date)
                if(tempMenu.image!=""){
                    binding.imageView5.setImageBitmap(decodeBase64ToBitmap(tempMenu.image.toString()))
                }
                try {
                    if(cekLike=="like") binding.imagefeed2.setImageResource(R.drawable.favoriteafter)
                    else binding.imagefeed2.setImageResource(R.drawable.favoritebefore)
                }catch (e:Exception){
                    Log.e("ERROR", e.toString())
                }

            }
        }

        binding.imagefeed2.setOnClickListener{
            ioScope.launch {
                var cek = vm.likeMenu(tempMenu.id.toString(), userPreference.getUser().id.toString())
                tempMenu = vm.getOneMenuById(menuId.toString())
                mainScope.launch {
                    binding.tvLikeFMO.setText(tempMenu.like.toString()+" Favorite")
                    try {
                        if(cek=="like") binding.imagefeed2.setImageResource(R.drawable.favoriteafter)
                        else if(cek=="unlike") binding.imagefeed2.setImageResource(R.drawable.favoritebefore)
                        else Toast.makeText(this@MenuFeedOpened, "No Internet Connection, Please check your connection", Toast.LENGTH_SHORT).show()
                    }catch (e:Exception){
                        Log.e("ERROR2", e.toString())
                    }
                }

            }
        }

        binding.ibAccount.setOnClickListener {
            finish()
        }
    }
    fun decodeBase64ToBitmap(base64Str: String): Bitmap {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}