package id.ac.istts.myfit.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "posts")
data class Post (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var id:Int,
    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "content") var content:String
)