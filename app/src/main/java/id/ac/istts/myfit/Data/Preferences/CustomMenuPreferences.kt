package id.ac.istts.myfit.Data.Preferences

import android.content.Context
import id.ac.istts.myfit.Data.Menu

internal class CustomMenuPreferences(context: Context) {
    companion object {
        private const val PREFS_NAME = "customMenuPref"
        private const val ID = "id"
        private const val USERID = "user_id"
        private const val NAME = "name"
        private const val INGREDIENTS = "ingredients"
        private const val NUTRITION = "nutrition"
        private const val HOWTOMAKE = "how_to_make"
        private const val NOTE = "note"
        private const val LIKE = "like"
        private const val DATE = "date"
        private const val STATUS = "status"
        private const val IMAGE = "image"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun addCustomMenu(customMenu: Menu) {
        val editor = preferences.edit()
        editor.putInt(ID, customMenu.id ?: 0)
        editor.putInt(USERID, customMenu.user_id ?: 0)
        editor.putString(NAME, customMenu.name?: "")
        editor.putString(INGREDIENTS, customMenu.ingredients?: "")
        editor.putString(NUTRITION, customMenu.nutrition?: "")
        editor.putString(HOWTOMAKE, customMenu.how_to_make?: "")
        editor.putString(NOTE, customMenu.note?: "")
        editor.putInt(LIKE, customMenu.like?:0)
        editor.putString(DATE, customMenu.date?: "")
        editor.putInt(STATUS, customMenu.status?:1)
        editor.putString(IMAGE, customMenu.image?: "")
        editor.apply()
    }

    fun clearCustomMenu() {
        val editor = preferences.edit()
        editor.clear()
        editor.commit()
    }

    fun getCustomMenu(): Menu {
        val model = Menu()
        model.id = preferences.getInt(CustomMenuPreferences.ID, 0)
        model.user_id = preferences.getInt(CustomMenuPreferences.USERID, 0)
        model.name = preferences.getString(CustomMenuPreferences.NAME, "")
        model.ingredients = preferences.getString(CustomMenuPreferences.INGREDIENTS, "")
        model.nutrition = preferences.getString(CustomMenuPreferences.NUTRITION, "")
        model.how_to_make = preferences.getString(CustomMenuPreferences.HOWTOMAKE, "")
        model.note = preferences.getString(CustomMenuPreferences.NOTE, "")
        model.like = preferences.getInt(CustomMenuPreferences.LIKE, 0)
        model.date = preferences.getString(CustomMenuPreferences.DATE, "")
        model.status = preferences.getInt(CustomMenuPreferences.STATUS, 1)
        model.image = preferences.getString(CustomMenuPreferences.IMAGE, "")
        return model
    }

    fun setMenuImage(image:String){
        val editor = preferences.edit()
        editor.putString(IMAGE, image)
        editor.apply()
    }
    fun setMenuName(name:String){
        val editor = preferences.edit()
        editor.putString(NAME, name)
        editor.apply()
    }
    fun setMenuIngredients(ingredients:String){
        val editor = preferences.edit()
        editor.putString(INGREDIENTS, ingredients)
        editor.apply()
    }

    fun setMenuNutrition(nutrition:String){
        val editor = preferences.edit()
        editor.putString(NUTRITION, nutrition)
        editor.apply()
    }
    fun setMenuHowToMake(howToMake:String){
        val editor = preferences.edit()
        editor.putString(HOWTOMAKE, howToMake)
        editor.apply()
    }
    fun setMenuNote(note:String){
        val editor = preferences.edit()
        editor.putString(NOTE, note)
        editor.apply()
    }
    fun setUserId(user_id:Int){
        val editor = preferences.edit()
        editor.putInt(USERID, user_id)
        editor.apply()
    }
    fun clearImage(){
        val editor = preferences.edit()
        editor.putString(IMAGE, "")
        editor.apply()
    }

}