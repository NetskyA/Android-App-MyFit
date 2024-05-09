package id.ac.istts.myfit.SignEmail

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.User
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.Period
import java.time.Year

class MenuSigninEmail2ViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)


    private val _age = MutableLiveData<Int>(0)
    val age:LiveData<Int>
        get() = _age

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateAge(day: String, month: String, year: String){
        if(day.isEmpty() || month.isEmpty() || year.isEmpty()){
            return
        }
        val intMonth = monthStringToInt(month)
        if(day.toInt() > 31 || day.toInt() < 1 || intMonth!! > 12 || intMonth < 1 || year.toInt() < 0){
            return
        }
        val birthDate = LocalDate.of(year.toInt(), intMonth, day.toInt())
        val today = LocalDate.now()
        _age.value = Period.between(birthDate, today).years
    }
    fun monthStringToInt(month: String): Int? {
        val monthMap = mapOf(
            "January" to 1, "February" to 2, "March" to 3,
            "April" to 4, "May" to 5, "June" to 6,
            "July" to 7, "August" to 8, "September" to 9,
            "October" to 10, "November" to 11, "December" to 12
        )
        return monthMap[month.capitalize()]
    }
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun cekData(name: String, day: String, month: String, year: String, gender: String, weight: String, height: String, bloodtype: String, allergy: String): String {
        val currentYear = Year.now().value
        val monthInt = monthStringToInt(month)

        if(name.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty() || gender.isEmpty()){
            return "Empty"
        }
        if(day.toInt() > 31 || day.toInt() < 1 || monthInt!! > 12 || monthInt < 1 || year.toInt() < 0 || year.toInt() >= currentYear){
            return "Invalid Date"
        }
        val weightInt = weight.toIntOrNull()
        val heightInt = height.toIntOrNull()
        if (weightInt != null) {
            if (weightInt <= 0) {
                return "Invalid Weight"
            }
        }
        if (heightInt != null) {
            if (heightInt <= 0) {
                return "Invalid Height"
            }
        }

        if(!bloodtype.isEmpty() && bloodtype.uppercase()!="A" && bloodtype.uppercase()!="B" && bloodtype.uppercase()!="AB" && bloodtype.uppercase()!="O"){
            return "Invalid Blood Type"
        }

        val intGender = if(gender == "Non-binary") 1 else if(gender == "Male") 2 else if(gender == "Female") 3 else 0
        userPreference.setRegisterEmail2(name, day.toInt(),monthInt,year.toInt(),intGender,weight,height,bloodtype,allergy,age.value!!)
        return withContext(Dispatchers.IO) {
            try {
                val response = MyFitApplication.retrofitUserService?.registerUser(userPreference.getUser())
                response!!.text
            }catch (e: Exception){
                return@withContext "Error"
            }
        }
    }
}