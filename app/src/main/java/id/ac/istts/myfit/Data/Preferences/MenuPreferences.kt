package id.ac.istts.myfit.Data.Preferences

import android.content.Context
import androidx.room.ColumnInfo
import id.ac.istts.myfit.Data.Menu

internal class MenuPreferences(context: Context) {
    companion object {
        private const val PREFS_NAME = "customMenuPref"
        private const val ID = "id"
        private const val NAME = "name"
        private const val INGREDIENTS = "ingredients"
        private const val NUTRITION = "nutrition"
        private const val HOWTOMAKE = "howToMake"
        private const val NOTE = "note"
        private const val LIKE = "like"
        private const val DATE = "date"
        private const val STATUS = "status"
        private const val IMAGE = "image"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun add(customMenu: Menu) {
        val editor = preferences.edit()
        editor.putInt(ID, customMenu.id ?: 0)
        editor.putString(NAME, customMenu.name)
        editor.putString(INGREDIENTS, customMenu.ingredients)
        editor.putString(NUTRITION, customMenu.nutrition)
        editor.putString(HOWTOMAKE, customMenu.howToMake)
        editor.putString(NOTE, customMenu.note)
        editor.putInt(LIKE, customMenu.like?:0)
        editor.putString(DATE, customMenu.date)
        editor.putInt(STATUS, customMenu.status?:0)
        editor.putString(IMAGE, customMenu.image)
        editor.apply()
    }
}