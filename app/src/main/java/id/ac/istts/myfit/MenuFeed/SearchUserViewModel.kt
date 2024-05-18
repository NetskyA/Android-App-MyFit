package id.ac.istts.myfit.MenuFeed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.AllMenuUser
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.User
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchUserViewModel (application: Application) : AndroidViewModel(application) {
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)

    suspend fun getUserByUsername(username: String): User {
        try {
            return withContext(Dispatchers.IO) {
                val result: User = MyFitApplication.retrofitUserService!!.getUserByUsername(username)
                result
            }
        } catch (e: Exception) {
            return User()
        }
    }

    suspend fun getUserMenu(id: Int): List<Menu> {
        try {
            return withContext(Dispatchers.IO) {
                val result: AllMenuUser = MyFitApplication.retrofitMenuService!!.getAllMenuUser(id)
                result.allMenuUser.sortedByDescending { it.id }
            }
        } catch (e: Exception) {
            return arrayListOf()
        }
    }

    fun countFav(menus: List<Menu>): Int {
        var fav = 0
        for (m in menus){
            fav += m.like?:0.toInt()
        }
        return fav
    }

    suspend fun sortOldest(menus: List<Menu>): List<Menu> {
        return menus.sortedBy { it.id }
    }

    suspend fun sortFav(menus: List<Menu>): List<Menu> {
        return menus.sortedByDescending { it.like }
    }
}