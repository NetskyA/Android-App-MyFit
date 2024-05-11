package id.ac.istts.myfit.ProfileSetting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Year

class ProfileSettingBodyViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)

    suspend fun cekData(gender: String, height: String, weight: String, bloodtype: String): String {
        var weightInt = weight.toIntOrNull()
        var heightInt = height.toIntOrNull()
        if (weightInt != null) {
            if (weightInt <= 0) {
                return "Weight"
            }
        }else{
            weightInt = 0
        }
        if (heightInt != null) {
            if (heightInt <= 0) {
                return "Height"
            }
        }else{
            heightInt = 0
        }

        if(!bloodtype.isEmpty() && bloodtype.uppercase()!="A" && bloodtype.uppercase()!="B" && bloodtype.uppercase()!="AB" && bloodtype.uppercase()!="O"){
            return "Blood"
        }

        val intGender = if(gender == "Non-binary") 1 else if(gender == "Male") 2 else if(gender == "Female") 3 else 0
        return withContext(Dispatchers.IO) {
            try {
                userPreference.setUpdateProfileBody(intGender, heightInt, weightInt, bloodtype)
                val response = MyFitApplication.retrofitUserService?.updateProfile(userPreference.getUser())
                return@withContext "Success"
            }catch (e: Exception){
                return@withContext "Error"
            }
        }
    }
}