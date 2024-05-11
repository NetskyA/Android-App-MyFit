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
import java.time.LocalDate
import java.time.Period
import java.time.Year

class ProfileSettingAccountViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)


    private val _age = MutableLiveData<Int>(0)
    val age: LiveData<Int>
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
    suspend fun cekData(name: String, username: String, email: String, phone: String, day: String, month: String, year: String): String {
        val currentYear = Year.now().value
        val monthInt = monthStringToInt(month)

        if( username.isEmpty() || email.isEmpty() || phone.isEmpty()){
            return "Empty"
        }

        if(day!="" && year!=""){
            if(day.toInt() > 31 || day.toInt() < 1 || monthInt!! > 12 || monthInt < 1 || year.toInt() < 0 || year.toInt() >= currentYear){
                return "Invalid Date"
            }
        }else{
            return "Invalid Date"
        }

        val tempUser = userPreference.getUser()

        var cekEmail = MyFitApplication.retrofitUserService?.checkEmail(tempUser.id.toString(), email)
        var cekUsername = MyFitApplication.retrofitUserService?.checkUsername(tempUser.id.toString(), username)
        var cekPhone = MyFitApplication.retrofitUserService?.checkPhone(tempUser.id.toString(), phone)

        if(cekEmail?.text.equals("Fail") || cekUsername?.text.equals("Fail") || cekPhone?.text.equals("Fail")){
            return "Same"
        }

        userPreference.setUpdateProfileAccount(name, day.toInt(),monthInt,year.toInt(), username ,email, phone, age.value!!)
        return withContext(Dispatchers.IO) {
            try {
                val response = MyFitApplication.retrofitUserService?.updateProfile(userPreference.getUser())
                response.toString()
            }catch (e: Exception){
                return@withContext "Error"
            }
        }
    }
}