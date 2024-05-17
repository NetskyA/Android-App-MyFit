package id.ac.istts.myfit.MenuDiet

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.istts.myfit.Data.ListMenuDiet
import id.ac.istts.myfit.Data.MenuDietData
import id.ac.istts.myfit.Data.MenuSearch
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuDietViewModel (application: Application) : AndroidViewModel(application) {
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
    val menuSearch: LiveData<MutableList<MenuSearch>>
        get() = _menuSearch
    suspend fun getMenuDiet()  = ioScope.launch {
        try {
            // Fetch menus from the repository
            val menusList = menuDietRepository.getAllMenuDiet(userPreference.getUser().id!!)
            // Log the fetched menus
            listMenuDiet = menusList
            for (menu in menusList){
                if(menu.menu != null){
                    val menuData = menuDietDataRepository.getAllMenuDietData(menu.menu.toString())
                    listMenu.addAll(menuData)
                }
            }
            listMenu = listMenu.distinctBy { it.id }.toMutableList()
            setMenu("Monday")
        } catch (e: Exception) {
            // Handle exceptions if needed
            Log.e("Error", "Error fetching menus: ${e.message}")
        }
    }

    suspend fun searchMenu(q: String) = ioScope.launch {
        try {
            // Fetch menus from the repository
            val menusList = menuService!!.searchAllMenu(q)
            // Log the fetched menus
            _menuSearch.postValue(menusList)
        } catch (e: Exception) {
            // Handle exceptions if needed
            Log.e("Error", "Error fetching menus: ${e.message}")
        }
    }
    fun setMenu(day: String){
        val menu = listMenuDiet.filter { it.day == day }
        if(menu.size == 0 || listMenu.size == 0){
            return
        }
        if(menu[0].menu == null){
            _menu.postValue(mutableListOf<MenuDietData>())
            return
        }
        Log.e("menu",menu[0].menu.toString())
        val tempData = menu[0].menu!!.split(",")
        var tempDataMenu = mutableListOf<MenuDietData>()
        for(data in tempData){
            tempDataMenu.add(listMenu.filter { it.id == data.toInt() }[0])
        }
        _menu.postValue(tempDataMenu)
    }

    suspend fun saveMenuDiet():String{
        try {
            menuDietRepository.saveAllMenuDiet(listMenuDiet)
            menuDietDataRepository.saveAllMenuDietData(listMenu)
            return "Success"
        } catch (e: Exception) {
            Log.e("error", e.message.toString() )
            return "Failed"
        }
    }

    fun addMenu(menuBaru:MenuSearch,day: String){
        val menu = listMenuDiet.filter { it.day == day }
        var tempData = mutableListOf<String>()
        if(menu[0].menu != null){
            tempData = menu[0].menu!!.split(",").toMutableList()
            if(tempData.contains(menuBaru.id.toString())){
                return
            }
        }
        listMenu.add(MenuDietData(null,menuBaru.id,menuBaru.user_id,menuBaru.name,menuBaru.ingredients,menuBaru.nutrition,menuBaru.how_to_make,menuBaru.note,menuBaru.like,menuBaru.date,menuBaru.status,menuBaru.image))
        tempData.add(menuBaru.id.toString())
        val tempDataString = tempData.joinToString(",")
        val tempMenu = listMenuDiet.filter { it.day == day }[0]
        tempMenu.menu = tempDataString
        listMenuDiet.remove(menu[0])
        listMenuDiet.add(tempMenu)
        setMenu(day)
    }
    fun deleteMenu(id:Int,day: String){
        val menu = listMenuDiet.filter { it.day == day }
        val tempData = menu[0].menu!!.split(",").toMutableList()
        tempData.remove(id.toString())
        val tempDataString = tempData.joinToString(",")
        val tempMenu = listMenuDiet.filter { it.day == day }[0]
        tempMenu.menu = tempDataString
        listMenuDiet.remove(menu[0])
        listMenuDiet.add(tempMenu)
        setMenu(day)
    }
    companion object{
        var listMenuDiet:MutableList<ListMenuDiet> = mutableListOf()
        var listMenu:MutableList<MenuDietData> = mutableListOf()
    }
}