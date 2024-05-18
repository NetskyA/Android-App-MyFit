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
    fun sortRecent() {
        ioScope.launch {
            try {
                // Fetch menus from the repository
                val menusList = allMenuUserRepository.sortOldest()
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
    fun sortFavorite() {
        ioScope.launch {
            try {
                // Fetch menus from the repository
                val menusList = allMenuUserRepository.sortFavorite()
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

    fun reset() {
        ioScope.launch {
            try {
                // Fetch menus from the repository
                val menusList = allMenuUserRepository.reset()
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
    fun countFavorite(menus:List<Menu>):Int{
        var fav = 0
        for (m in menus){
            fav+=m.like!!.toInt()
        }
        return fav
    }

//    fun savedMenu(id: String): ArrayList<Menu>{
//        var saveMenus:ArrayList<Menu> = arrayListOf()
//        ioScope.launch {
//            try {
//                saveMenus = ArrayList(MyFitApplication.retrofitLikeService!!.savedMenu(id))
//                return ArrayList(saveMenus)
////                Log.d("TEMP", saveMenus.toString())
//            }catch(e:Exception){
//                Log.e("error ap nih", e.toString())
//                saveMenus = arrayListOf()
//                return saveMenus
//            }
//        }
////        return ArrayList(saveMenus)
//    }
    suspend fun savedMenu(id: String): ArrayList<Menu> {
        return withContext(Dispatchers.IO) {
            try {
                val saveMenus = ArrayList(MyFitApplication.retrofitLikeService!!.savedMenu(id))
                saveMenus
            } catch (e: Exception) {
                Log.e("error ap nih", e.toString())
                arrayListOf()
            }
        }
    }
}