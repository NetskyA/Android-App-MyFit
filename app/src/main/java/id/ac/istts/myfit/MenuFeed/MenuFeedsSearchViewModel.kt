package id.ac.istts.myfit.MenuFeed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.Preferences.CustomMenuPreferences
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.SearchResult
import id.ac.istts.myfit.Data.User
import id.ac.istts.myfit.Data.tempUser
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuFeedsSearchViewModel (application: Application) : AndroidViewModel(application) {
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
    suspend fun search(id:String, keyword: String): List<tempUser> {
        try {
            return withContext(Dispatchers.IO) {
                val searchResult: SearchResult = MyFitApplication.retrofitUserService!!.search(id, keyword)
                val users: List<tempUser> = searchResult.searchUser
                users
            }
        }catch (e:Exception){
            return arrayListOf()
        }
    }

}