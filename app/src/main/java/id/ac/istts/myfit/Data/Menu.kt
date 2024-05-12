package id.ac.istts.myfit.Data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Entity(tableName = "menus")
@Parcelize
data class Menu (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var id:Int? = 0,
    @ColumnInfo(name = "user_id") var userId:Int? = 0,
    @ColumnInfo(name = "name") var name:String? = "",
    @ColumnInfo(name = "ingredients") var ingredients:String? = "",
    @ColumnInfo(name = "nutrition") var nutrition:String? = "",
    @ColumnInfo(name = "how_to_make") var how_to_make:String? = "",
    @ColumnInfo(name = "note") var note:String? = "",
    @ColumnInfo(name = "like") var like:Int? = 0,
    @ColumnInfo(name = "date") var date:String? = "",
    @ColumnInfo(name = "status") var status:Int? = 1,
    @ColumnInfo(name = "image") var image:String? = "",
): Parcelable
