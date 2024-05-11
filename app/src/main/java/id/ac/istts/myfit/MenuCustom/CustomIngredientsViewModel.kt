package id.ac.istts.myfit.MenuCustom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.istts.myfit.Data.ImageData
import id.ac.istts.myfit.Data.Preferences.CustomMenuPreferences
import id.ac.istts.myfit.Data.Preferences.UserPreference
import id.ac.istts.myfit.MyFitApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomIngredientsViewModel (application: Application) : AndroidViewModel(application) {
    private val customMenuPreferences: CustomMenuPreferences = CustomMenuPreferences(application)

    suspend fun setMenuImage(image: String){
        customMenuPreferences.setMenuImage(image)
    }
    suspend fun setMenuName(name: String){
        customMenuPreferences.setMenuName(name)
    }
    suspend fun setMenuIngredients(ingredients: String){
        customMenuPreferences.setMenuIngredients(ingredients)
    }
    suspend fun setMenuNutrition(nutrition: String){
        customMenuPreferences.setMenuNutrition(nutrition)
    }
    suspend fun setMenuHowToMake(howToMake: String){
        customMenuPreferences.setMenuHowToMake(howToMake)
    }
    suspend fun setMenuNote(note: String){
        customMenuPreferences.setMenuNote(note)
    }
//    suspend fun uploadImage(image: String): String{
//        return withContext(Dispatchers.IO) {
//            try {
//                val imageData = ImageData(customMenuPreferences.getUser().id.toString(), image)
//                val response = MyFitApplication.retrofitUserService?.uploadImage(imageData)
//                userPreference.setImage(image)
//                response.toString()
//            }catch (e: Exception){
//                return@withContext "Fail"
//            }
//        }
//    }
}