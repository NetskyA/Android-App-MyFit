package id.ac.istts.myfit.Data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Entity(tableName = "listmenudiet")
@Parcelize
data class ListMenuDiet (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var id:Int? = 0,
    @ColumnInfo(name = "user_id") var user_id:Int? = 0,
    @ColumnInfo(name = "menu") var menu:String? = "",
    @ColumnInfo(name = "day") var day:String? = "",
): Parcelable