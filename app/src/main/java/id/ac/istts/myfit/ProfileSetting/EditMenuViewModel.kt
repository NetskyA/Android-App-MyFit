package id.ac.istts.myfit.ProfileSetting

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.ErrorMsg
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditMenuViewModel (application: Application) : AndroidViewModel(application) {
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    private val allMenuUserRepository = MyFitApplication.allMenuUserRepository
    suspend fun getOneMenuById(id: String): Menu {
        try {
            return withContext(Dispatchers.IO) {
                val menuResult: Menu = MyFitApplication.retrofitMenuService!!.getOneMenuById(id)
                menuResult
            }
        } catch (e: Exception) {
            return Menu()
        }
    }

    suspend fun deleteMenuById(menu: Menu): String {
        try {
            return withContext(Dispatchers.IO) {
                var result = allMenuUserRepository.deleteMenu(menu)
                result
            }
        } catch (e: Exception) {
            return "Error"
        }
    }
    suspend fun editMenuById(id:String, name:String, nutrition:String, ingredients:String
    , howToMake:String, notes:String, image:String): String {
        try {
            return withContext(Dispatchers.IO) {
                var result = MyFitApplication.retrofitMenuService!!.editMenuById(id, name, nutrition,
                    ingredients, howToMake, notes, image)
                result.text
            }
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
            return "Error"
        }
    }
}