package id.ac.istts.myfit.Data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

data class tempUser(
    val username: String,
    val image: String
)

data class SearchResult(
    val searchUser: List<tempUser>
)

