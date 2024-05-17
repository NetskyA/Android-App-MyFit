package id.ac.istts.myfit.SignAll

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuSigninAllViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)
    suspend fun checkEmail(
        email: String,
        name: String,
    ): String {
        return withContext(Dispatchers.IO) {
            try {
                val response = MyFitApplication.retrofitUserService?.loginGoogle(email)
                userPreference.setEmailandName(email, name)
                if(response!!.password.toString()=="0"){
                    return@withContext "New User"
                }
                userPreference.login(response)
                return@withContext "Ok"
            }catch (e: Exception){
                return@withContext "Error"
            }
        }
    }
}
