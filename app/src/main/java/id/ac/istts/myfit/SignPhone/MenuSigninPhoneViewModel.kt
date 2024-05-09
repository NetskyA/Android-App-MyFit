package id.ac.istts.myfit.SignPhone

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.User
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuSigninPhoneViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)

    fun extractCountryCode(input: String): String? {
        val regex = "\\+\\d+".toRegex()
        return regex.find(input)?.value
    }

    suspend fun cekPhone(code: String,phone: String):String{
        if(phone.isEmpty()){
            return "Empty"
        }
        val countryCode = extractCountryCode(code)
        val phoneNum = "${countryCode} ${phone}"
        return withContext(Dispatchers.IO) {
            try {
                val response = MyFitApplication.retrofitUserService?.cekPhoneNumber(phoneNum)
                if(response!!.password.toString()=="0"){
                    userPreference.setPhone(phoneNum)
                    return@withContext "Not Registered"
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