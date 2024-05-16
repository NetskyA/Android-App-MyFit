package id.ac.istts.myfit.ProfileSetting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditMenuViewModel (application: Application) : AndroidViewModel(application) {
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
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

//    suspend fun getUsername(id: String): String {
//        try {
//            return withContext(Dispatchers.IO) {
//                val result: String = MyFitApplication.retrofitUserService!!.getUsername(id)
//                result
//            }
//        } catch (e: Exception) {
//            return ""
//        }
//    }
//
//    suspend fun getLikeMenu(menu_id: String, user_id: String): String {
//        try {
//            return withContext(Dispatchers.IO) {
//                val result: String =
//                    MyFitApplication.retrofitLikeService!!.getLikeMenu(menu_id, user_id)
//                result
//            }
//        } catch (e: Exception) {
//            return "unlike"
//        }
//    }
//
//    suspend fun likeMenu(menu_id: String, user_id: String): String {
//        try {
//            return withContext(Dispatchers.IO) {
//                val result: String =
//                    MyFitApplication.retrofitLikeService!!.likeMenu(menu_id, user_id)
//                result
//            }
//        } catch (e: Exception) {
//            return "error"
//        }
//    }
}