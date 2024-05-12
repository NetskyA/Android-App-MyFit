package id.ac.istts.myfit.Data.Source.Remote

import id.ac.istts.myfit.Data.ErrorMsg
import id.ac.istts.myfit.Data.ImageData
import id.ac.istts.myfit.Data.SearchResult
import id.ac.istts.myfit.Data.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface UserService {
    @POST("api/users/register")
    suspend fun registerUser(@Body user: User): User

    @POST("api/users/cekEmailandUsername")
    suspend fun cekEmailandUsername(@Body user: User): ErrorMsg

    @POST("api/users/login")
    suspend fun loginUser(@Body user: User): User

    @POST("api/users/cekPhoneNumber")
    suspend fun cekPhoneNumber(@Query("phone") phone:String): User

    @POST("api/users/sendCode")
    suspend fun sendCode(@Query("email") email:String):ErrorMsg

    @POST("api/users/checkPass")
    suspend fun checkPass(@Query("id") id:String, @Query("password") password:String, @Query("newPassword") newPassword:String):User

    @POST("api/users/verifyOtp")
    suspend fun verifyOtp(@Query("email") email:String,@Query("otp") otp:String):ErrorMsg

    @POST("api/users/updateProfile")
    suspend fun updateProfile(@Body user: User): ErrorMsg

    @POST("api/users/checkEmail")
    suspend fun checkEmail(@Query("id") id:String, @Query("email") email:String):ErrorMsg

    @POST("api/users/checkPhone")
    suspend fun checkPhone(@Query("id") id:String, @Query("phone") phone:String):ErrorMsg

    @POST("api/users/checkUsername")
    suspend fun checkUsername(@Query("id") id:String, @Query("username") username:String):ErrorMsg

    @POST("api/users/uploadImage")
    suspend fun uploadImage(@Body imageData: ImageData):ErrorMsg

    @POST("api/users/getImage")
    suspend fun getImage(@Query("id") id:String):ErrorMsg

    @GET("api/users/test")
    suspend fun test():ErrorMsg

    @GET("api/users/search")
    suspend fun search(@Query("id") id:String, @Query("keyword") keyword:String): SearchResult
}