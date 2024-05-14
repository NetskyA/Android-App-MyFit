package id.ac.istts.myfit.MenuDiet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.Preferences.UserPreference

class MenuDietViewModel (application: Application) : AndroidViewModel(application) {
    private val userPreference: UserPreference = UserPreference(application)

}