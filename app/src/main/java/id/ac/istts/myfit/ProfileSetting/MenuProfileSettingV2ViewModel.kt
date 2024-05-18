package id.ac.istts.myfit.ProfileSetting

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.ImageData
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuProfileSettingV2ViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)

    suspend fun uploadImage(image: String): String{
        return withContext(Dispatchers.IO) {
            try {
                val imageData = ImageData(userPreference.getUser().id.toString(), image)
                val response = MyFitApplication.retrofitUserService?.uploadImage(imageData)
                userPreference.setImage(image)
                response.toString()
            }catch (e: Exception){
                return@withContext "Fail"
            }
        }
    }

    suspend fun deleteAccount(pass: String): String{
        return withContext(Dispatchers.IO) {
            try {
                val response = MyFitApplication.retrofitUserService?.deleteAccount(userPreference.getUser().id.toString(), pass)
                Log.e("RESPONSE", response!!.text)
                return@withContext response.text
            }catch (e: Exception){
                return@withContext "Fail"
            }
        }
    }
}