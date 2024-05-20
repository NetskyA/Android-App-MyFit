package id.ac.istts.myfit.MenuCustom

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.ImageData
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Preferences.CustomMenuPreferences
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.User
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomResultViewModel(application: Application) : AndroidViewModel(application) {
    private val customMenuPreferences: CustomMenuPreferences = CustomMenuPreferences(application)
    private val userPreference: UserPreference = UserPreference(application)
    private val allMenuUserRepository = MyFitApplication.allMenuUserRepository
    suspend fun upload(): String{
        customMenuPreferences.setUserId(userPreference.getUser().id!!.toInt())
//        Log.e("USER ID MENU", customMenuPreferences.getCustomMenu().user_id.toString())
        return withContext(Dispatchers.IO) {
            try {
//                val response = MyFitApplication.retrofitMenuService?.uploadMenu(customMenuPreferences.getCustomMenu())
                allMenuUserRepository.sortOldest()
                val response = allMenuUserRepository.uploadMenu(customMenuPreferences.getCustomMenu())
                response!!.text.toString()
            }catch (e: Exception){
                Log.e("Error apa ni", e.toString())
                return@withContext "Fail"
            }
        }
    }
//    suspend fun clear(): List<Menu>{
//        customMenuPreferences.clearCustomMenu()
//        return allMenuUserRepository.getAllMenus(userPreference.getUser().id!!.toInt())
//    }
    suspend fun clear(){
        customMenuPreferences.clearCustomMenu()
    }
    suspend fun getAllMenuUser(id: Int) {
        try {
            allMenuUserRepository.getAllMenus(id)
        } catch (e: Exception) {
            Log.e("Error", "Error fetching menus: ${e.message}")
        }
    }
}