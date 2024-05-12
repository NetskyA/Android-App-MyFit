package id.ac.istts.myfit.ProfileSetting

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.Data.User
import id.ac.istts.myfit.MyFitApplication
import id.ac.istts.myfit.Util.CheckValid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileSettingPasswordViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _timerValue = MutableLiveData(-1)
    val timerValue: LiveData<Int> = _timerValue

    private var countDownTimer: CountDownTimer? = null
    fun startTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timerValue.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                _timerValue.value = 0
            }
        }.start()
    }

    fun stopTimer() {
        countDownTimer?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer() // Cancel the timer when the ViewModel is about to be destroyed
    }

    suspend fun sendOtp(email: String):String{
        if(!(CheckValid.isValidEmail(email))){
            return "Invalid Email"
        }
        try {
            val response = MyFitApplication.retrofitUserService?.cekEmailandUsername(User(email = email,username = email))
            if(response!!.text=="Ok"){
                return "Invalid Email"
            }
        } catch (e: Exception) {
            Log.e("response", e.toString())
            return "Error"
        }
        try {
            MyFitApplication.retrofitUserService?.sendCode(email)
            viewModelScope.launch {
                startTimer()
            }
            return "Ok"
        } catch (e: Exception) {
            Log.e("response", e.toString())
            return "Error"
        }
    }
    suspend fun cekOtp(code: String):String{
        var user = userPreference.getUser()
        return withContext(Dispatchers.IO) {
            try {
                val response = MyFitApplication.retrofitUserService?.verifyOtp(user.email!!,code)
                return@withContext response!!.text
            } catch (e: Exception) {
                Log.e("response", e.toString())
                return@withContext "Error"
            }
        }
    }

    suspend fun cekPassword(currentPass: String, newPass: String, cNewPass: String):String{
        var user = userPreference.getUser()

        if(currentPass.isEmpty() || newPass.isEmpty() || cNewPass.isEmpty()){
            return "Empty"
        }
        if(newPass!=cNewPass){
            return "New Password and Confirm Password must be the same"
        }
        if(!(CheckValid.validatePassword(newPass))){
            return "Invalid Password"
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = MyFitApplication.retrofitUserService?.checkPass(user.id!!.toString(),currentPass,newPass)
                if(response!!.password.toString()=="0") {
                    return@withContext "Password not match"
                }else{
                    return@withContext "Ok"
                }
            } catch (e: Exception) {
                Log.e("response", e.toString())
                return@withContext "Error"
            }
        }
    }
}