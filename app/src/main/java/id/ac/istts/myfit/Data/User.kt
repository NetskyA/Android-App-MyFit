package id.ac.istts.myfit.Data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Entity(tableName = "users")
@Parcelize
data class User (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var id:Int? = null,
    @ColumnInfo(name = "email") var email:String? = null,
    @ColumnInfo(name = "username") var username:String? = null,
    @ColumnInfo(name = "phone") var phone:String? = null,
    @ColumnInfo(name = "name") var name:String? = null,
    @ColumnInfo(name = "password") var password:String? = null,
    @ColumnInfo(name = "dob") var dob:String? = null,
    @ColumnInfo(name = "gender") var gender:Int? = null,
    @ColumnInfo(name = "height") var height:Int? = null,
    @ColumnInfo(name = "weight") var weight:Int? = null,
    @ColumnInfo(name = "age") var age:Int? = null,
    @ColumnInfo(name = "blood_type") var blood_type:String? = null,
    @ColumnInfo(name = "allergy") var allergy:String? = null,
    @ColumnInfo(name = "image") var image:String? = null,
): Parcelable