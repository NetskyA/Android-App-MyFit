package id.ac.istts.myfit.ProfileSetting

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.ac.istts.myfit.Data.AllMenuUser
import id.ac.istts.myfit.Data.AllMenuUserRepository
import id.ac.istts.myfit.Data.ImageData
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuProfileViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)
    private val allMenuUserRepository = MyFitApplication.allMenuUserRepository
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val _menus = MutableLiveData<ArrayList<Menu>>()
    val menus: LiveData<ArrayList<Menu>>
        get() = _menus

    // Function to fetch all menus for a given user ID
    fun getAllMenuUser(id: Int) {
        ioScope.launch {
            try {
                // Fetch menus from the repository
                val menusList = allMenuUserRepository.getAllMenus(id)
                // Update the LiveData with the fetched menus on the main thread
                withContext(Dispatchers.Main) {
                    _menus.value = ArrayList(menusList) // Convert the list to ArrayList
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e("Error", "Error fetching menus: ${e.message}")
            }
        }
    }
//    fun getAllMenuUser(id: Int): ArrayList<Menu> {
//        var menus:ArrayList<Menu> = arrayListOf()
//        try {
//            ioScope.launch {
//                menus = allMenuUserRepository.getAllMenus(id)
//                Log.d("BBBBBBBBBBBBBBBBBB", menus.toString())
//                mainScope.launch {
//                    return menus
//                }
//            }
//        }catch (err:Exception){
//
//        }
//        return menus
//    }
//    fun getPost(id:Int){
//        ioScope.launch {
//            _menu.postValue(allMenuUserRepository.getMenuById(id))
//        }
//    }
//
//    fun createPost(title:String, content:String){
//        ioScope.launch {
//            postRepository.createPost(Post(0, title, content))
//        }
//    }
//
//    fun updatePost(title:String, content:String){
//        if(post.value != null){
//            ioScope.launch {
//                postRepository.updatePost(Post(post.value!!.id, title, content))
//            }
//        }
//    }
//    suspend fun uploadImage(image: String): String{
//        return withContext(Dispatchers.IO) {
//            try {
//                val imageData = ImageData(userPreference.getUser().id.toString(), image)
//                val response = MyFitApplication.retrofitUserService?.uploadImage(imageData)
//                userPreference.setImage(image)
//                response.toString()
//            }catch (e: Exception){
//                return@withContext "Fail"
//            }
//        }
//    }
}