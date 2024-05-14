package id.ac.istts.myfit.Data.Source.Remote

import id.ac.istts.myfit.Data.AllMenuUser
import id.ac.istts.myfit.Data.ErrorMsg
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.RandomMenu
import id.ac.istts.myfit.Data.RandomMenuData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MenuService {
    @POST("api/menus/upload")
    suspend fun uploadMenu(@Body menu: Menu): ErrorMsg

    @GET("api/menus/getAllMenuUser")
    suspend fun getAllMenuUser(@Query("user_id") user_id:Int): AllMenuUser
    @GET("api/menus/getRandomMenu")
    suspend fun getRandomMenu(@Query("user_id") user_id:Int): RandomMenuData
}