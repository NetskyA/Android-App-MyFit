package id.ac.istts.myfit.Data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MenuSearch (
    var id:Int? = 0,
    var user_id:Int? = 0,
    var name:String? = "",
    var ingredients:String? = "",
    var nutrition:String? = "",
    var how_to_make:String? = "",
    var note:String? = "",
    var like:Int? = 0,
    var date:String? = "",
    var status:Int? = 1,
    var image:String? = "",
    val nama_user:String? = ""
)