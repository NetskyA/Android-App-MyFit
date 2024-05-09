package id.ac.istts.myfit.SignPhone

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuSigninPhone2ViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _timerValue = MutableLiveData(0)
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

    suspend fun sendOtp():String{
        var user = userPreference.getUser()
        try {
            MyFitApplication.retrofitUserService?.sendCode(user.email!!)
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
}