package id.ac.istts.myfit.Data.Source.Remote

import id.ac.istts.myfit.Data.AllMenuUser
import id.ac.istts.myfit.Data.ErrorMsg
import id.ac.istts.myfit.Data.ImageData
import id.ac.istts.myfit.Data.ListMenuDiet
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.MenuDietData
import id.ac.istts.myfit.Data.MenuSearch
import id.ac.istts.myfit.Data.RandomMenuData
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface MenuService {
    @POST("api/menus/upload")
    suspend fun uploadMenu(@Body menu: Menu): ErrorMsg

    @GET("api/menus/getAllMenuUser")
    suspend fun getAllMenuUser(@Query("user_id") user_id:Int): AllMenuUser
    @GET("api/menus/getRandomMenu")
    suspend fun getRandomMenu(@Query("user_id") user_id:Int): RandomMenuData

    @GET("api/menus/getMenuDiet")
    suspend fun getMenuDiet(@Query("user_id") user_id:Int): List<ListMenuDiet>

    @GET("api/menus/getmenuById")
    suspend fun getMenuById(@Query("id") id:String): List<MenuDietData>

    @GET("api/menus/searchAllMenu")
    suspend fun searchAllMenu(@Query("q") q:String): MutableList<MenuSearch>

    @POST("api/menus/saveMenuDiet")
    suspend fun saveMenuDiet(@Body menuDietData: MutableList<ListMenuDiet>): ErrorMsg

    @GET("api/menus/getOneMenuById")
    suspend fun getOneMenuById(@Query("id") id:String): Menu

    @GET("api/menus/searchImageByQuery")
    suspend fun searchImageByQuery(@Query("id") id:String, @Query("q") q:String): List<Menu>

    @GET("api/menus/recommendMenu")
    suspend fun recommendMenu(@Query("id") id:String, @Query("limit") limit:String = "30"): List<Menu>

    @DELETE("api/menus/deleteMenuById")
    suspend fun deleteMenuById(@Query("id") id:String): ErrorMsg

    @PUT("api/menus/editMenuById")
    suspend fun editMenuById(@Body menu: Menu): ErrorMsg

}