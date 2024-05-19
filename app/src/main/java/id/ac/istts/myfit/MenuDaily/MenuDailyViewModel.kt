package id.ac.istts.myfit.MenuDaily

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.istts.myfit.Data.ListMenuDiet
import id.ac.istts.myfit.Data.MenuDietData
import id.ac.istts.myfit.Data.MenuSearch
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MenuDiet.MenuDietViewModel
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuDailyViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)
    private val menuDietRepository = MyFitApplication.listMenuDietRepository
    private val menuDietDataRepository = MyFitApplication.menuDietDataRepository
    private val menuService = MyFitApplication.retrofitMenuService
    private val ioScope = CoroutineScope(Dispatchers.IO)
    lateinit var vm: MenuDietViewModel
    private val _menu = MutableLiveData<MutableList<MenuDietData>>()
    private val _menuSearch = MutableLiveData<MutableList<MenuSearch>>()
    val menu: LiveData<MutableList<MenuDietData>>
        get() = _menu
    suspend fun getMenuDiet()  = ioScope.launch {
        try {
            // Fetch menus from the repository
            val menusList = menuDietRepository.getAllMenuDiet(userPreference.getUser().id!!)
            // Log the fetched menus
            listMenuDiet = menusList
            for (menu in menusList){
                if(menu.menu != null && menu.menu != ""){
                    val menuData = menuDietDataRepository.getAllMenuDietData(menu.menu.toString())
                    listMenu.addAll(menuData)
                }
            }
            listMenu = listMenu.distinctBy { it.id }.toMutableList()
            _menu.postValue(listMenu)
        } catch (e: Exception) {
            // Handle exceptions if needed
            Log.e("Error", "Error fetching menus: ${e.message}")
        }
    }

    fun setMenu(day: String):MutableList<MenuDietData>{
        val menu = listMenuDiet.filter { it.day == day }
        Log.e("MenuHome", menu.toString())
        if(menu[0].menu == null || menu[0].menu == ""){
            return mutableListOf<MenuDietData>()
        }
        val tempData = menu[0].menu!!.split(",")
        var tempDataMenu = mutableListOf<MenuDietData>()
        for(data in tempData){
            tempDataMenu.add(listMenu.filter { it.id == data.toInt() }[0])
        }
        return tempDataMenu
    }

    companion object{
        var listMenuDiet:MutableList<ListMenuDiet> = mutableListOf()
        var listMenu:MutableList<MenuDietData> = mutableListOf()
    }
}