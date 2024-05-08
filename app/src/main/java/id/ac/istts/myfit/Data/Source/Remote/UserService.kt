package id.ac.istts.myfit.Data.Source.Remote

import id.ac.istts.myfit.Data.ErrorMsg
import id.ac.istts.myfit.Data.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface UserService {
    @POST("api/users/register")
    suspend fun registerUser(@Body user: User): ErrorMsg

    @POST("api/users/cekEmailandUsername")
    suspend fun cekEmailandUsername(@Body user: User): ErrorMsg

}