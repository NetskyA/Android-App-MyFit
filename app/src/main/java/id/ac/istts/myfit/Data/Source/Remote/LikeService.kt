package id.ac.istts.myfit.Data.Source.Remote

import id.ac.istts.myfit.Data.AllMenuUser
import id.ac.istts.myfit.Data.ErrorMsg
import id.ac.istts.myfit.Data.ListMenuDiet
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.MenuDietData
import id.ac.istts.myfit.Data.MenuSearch
import id.ac.istts.myfit.Data.RandomMenuData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LikeService {
    @POST("api/likes/likeMenu")
    suspend fun likeMenu(@Query("menu_id") menu_id:String, @Query("user_id") user_id:String): String

    @GET("api/likes/getLikeMenu")
    suspend fun getLikeMenu(@Query("menu_id") menu_id:String, @Query("user_id") user_id:String): String

    @GET("api/likes/savedMenu")
    suspend fun savedMenu(@Query("id") id:String): List<Menu>
}