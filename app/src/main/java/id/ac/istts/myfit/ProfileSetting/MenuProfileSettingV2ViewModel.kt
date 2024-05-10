package id.ac.istts.myfit.ProfileSetting

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuProfileSettingV2ViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)

    suspend fun uploadImage(image: String): String{
        userPreference.setImage(image)
        return withContext(Dispatchers.IO) {
            try {
                val response = MyFitApplication.retrofitUserService?.uploadImage(userPreference.getUser().id.toString(), image)
                response.toString()
            }catch (e: Exception){
                return@withContext e.toString()
            }
        }
    }
}