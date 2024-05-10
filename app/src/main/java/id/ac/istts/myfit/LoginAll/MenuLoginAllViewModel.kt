package id.ac.istts.myfit.LoginAll

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.User
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuLoginAllViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)
    suspend fun cekLogin(data: String, password: String): String {
        if(data.isEmpty() || password.isEmpty()){
            return "Empty"
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = MyFitApplication.retrofitUserService?.loginUser(
                    User(
                        email = data,
                        username = data,
                        password = password
                    )
                )
                Log.e("USER", response.toString())
                if(response!!.password.toString()=="0"){
                    return@withContext "User not found"
                }else if(response.password.toString()=="1"){
                    return@withContext "Password not match"
                }
                userPreference.login(response)
                return@withContext "Ok"
            } catch (e: Exception) {
                Log.e("response", e.toString())
                return@withContext "Error"
            }
        }
    }
}