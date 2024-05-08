package id.ac.istts.myfit.SignEmail

import android.content.Intent
import androidx.lifecycle.ViewModel
import id.ac.istts.myfit.Data.ErrorMsg
import id.ac.istts.myfit.Data.User
import id.ac.istts.myfit.MyFitApplication
import id.ac.istts.myfit.Util.CheckValid
import id.ac.istts.myfit.databinding.ActivityMenuSigninEmailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuSigninEmailViewModel: ViewModel() {
   suspend fun checkEmaiandUsername(
       email: String,
       username: String,
       password: String,
       cpass: String,
   ): String {
       if(email.isEmpty() || username.isEmpty() || password.isEmpty() || cpass.isEmpty()){
            return "Empty"
       }

       if(!(CheckValid.isValidEmail(email))){
           return "Invalid Email"
       }

       if(!(CheckValid.validatePassword(password))){
              return "Invalid Password"
       }

       if(password != cpass){
           return "Password not match"
       }

       return withContext(Dispatchers.IO) {
           try {
               val response = MyFitApplication.retrofitUserService?.cekEmailandUsername(User(email = email, username = username))
               response!!.text
           }catch (e: Exception){
               return@withContext "Error"
           }
       }
   }
}