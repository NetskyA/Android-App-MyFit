package id.ac.istts.myfit.MenuFeed

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.RandomMenu
import id.ac.istts.myfit.Data.RandomMenuData
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class MenuFeedsViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)
    private val randomMenuRepository = MyFitApplication.randomMenuRepository
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val _menus = MutableLiveData<MutableList<RandomMenu>>()
    val menus: LiveData<MutableList<RandomMenu>>
        get() = _menus

    fun clearDataMenus(){
        ioScope.launch {
            try {
                randomMenuRepository.clearDataMenus()
            }catch (e:Exception){
                Log.e("Error", "${e.message}")
            }
        }
    }
    // Function to fetch all menus for a given user ID
    fun getAllMenus(id: Int) {
        ioScope.launch {
            try {
                // Fetch menus from the repository
                val menusList = randomMenuRepository.getAllMenus(id)
                // Update the LiveData with the fetched menus on the main thread
                withContext(Dispatchers.Main) {
                    _menus.value = menusList.toMutableList()
                }// Convert the list to ArrayList

            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e("Error", "${e.message}")
            }
        }
    }
}