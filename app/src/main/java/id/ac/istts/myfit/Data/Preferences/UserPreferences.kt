package id.ac.istts.myfit.Data.Preferences

import android.content.Context
import id.ac.istts.myfit.Data.User

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "userPref"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val USERNAME = "username"
        private const val PHONE = "phone"
        private const val DOB = "dob"
        private const val GENDER = "gender"
        private const val HEIGHT = "height"
        private const val WEIGHT = "weight"
        private const val AGE = "age"
        private const val BLOOD_TYPE = "blood_type"
        private const val ALLERGY = "allergy"
        private const val IMAGE = "image"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // USER
    fun setRegisterEmail1(username: String, email: String,password: String) {
        val editor = preferences.edit()
        editor.putString(USERNAME, username)
        editor.putString(EMAIL, email)
        editor.putString(PASSWORD, password)
        editor.apply()
    }
    fun setRegisterEmail2(name: String, day: String, month: String, year: String, gender: Int, weight: String, height: String, bloodtype: String, allergy: String,age: Int) {
        val editor = preferences.edit()
        editor.putString(NAME, name)
        editor.putString(DOB, "$day-$month-$year")
        editor.putInt(GENDER, gender)
        editor.putInt(WEIGHT, weight.toIntOrNull() ?: 0)
        editor.putInt(HEIGHT, height.toIntOrNull() ?: 0)
        editor.putString(BLOOD_TYPE, bloodtype)
        editor.putString(ALLERGY, allergy)
        editor.putInt(AGE, age)
        editor.apply()
    }
    fun getUser(): User {
        val model = User()
        model.name = preferences.getString(NAME, "")
        model.email = preferences.getString(EMAIL, "")
        model.password = preferences.getString(PASSWORD, "")
        model.username = preferences.getString(USERNAME, "")
        model.phone = preferences.getString(PHONE, "")
        model.dob = preferences.getString(DOB, "")
        model.gender = preferences.getInt(GENDER, 0)
        model.height = preferences.getInt(HEIGHT, 0)
        model.weight = preferences.getInt(WEIGHT, 0)
        model.age = preferences.getInt(AGE, 0)
        model.blood_type = preferences.getString(BLOOD_TYPE, "")
        model.allergy = preferences.getString(ALLERGY, "")
        model.image = preferences.getString(IMAGE, "")
        return model
    }
}