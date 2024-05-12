package id.ac.istts.myfit.Data.Source.Remote

import id.ac.istts.myfit.Data.ErrorMsg
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.User
import retrofit2.http.Body
import retrofit2.http.POST

interface MenuService {
    @POST("api/menus/upload")
    suspend fun uploadMenu(@Body menu: Menu): ErrorMsg
}