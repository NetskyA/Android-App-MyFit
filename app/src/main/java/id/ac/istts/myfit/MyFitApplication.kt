package id.ac.istts.myfit

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.ac.istts.myfit.Data.AllMenuUserRepository
import id.ac.istts.myfit.Data.ListMenuDietRepository
import id.ac.istts.myfit.Data.MenuDietDataRepository
import id.ac.istts.myfit.Data.RandomMenuRepository
import id.ac.istts.myfit.Data.Source.Local.AppDatabase
import id.ac.istts.myfit.Data.Source.Remote.LikeService
import id.ac.istts.myfit.Data.Source.Remote.MenuService
import id.ac.istts.myfit.Data.Source.Remote.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MyFitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initRepository(baseContext)
    }

    companion object {
        lateinit var allMenuUserRepository: AllMenuUserRepository
        lateinit var randomMenuRepository: RandomMenuRepository
        lateinit var listMenuDietRepository: ListMenuDietRepository
        lateinit var menuDietDataRepository: MenuDietDataRepository

        var retrofitUserService:UserService? = null
        var retrofitMenuService:MenuService? = null
        var retrofitLikeService:LikeService? = null

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS).build()
        fun initRepository(context: Context) {
            val roomDB = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "mdpinf20232m10"
            ).build()
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://192.168.1.5:3000")
                .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                .build()

            retrofitUserService = retrofit.create(UserService::class.java)
            retrofitMenuService = retrofit.create(MenuService::class.java)
            retrofitLikeService = retrofit.create(LikeService::class.java)

            // load repo dari room saja
//            postRepository = DefaultPostRepository(roomDB)

            // load repo dari retrofit saja
//            postRepository = DefaultPostRepository(retrofit.create(MdpService::class.java))

            // load repo hybrid
            allMenuUserRepository = AllMenuUserRepository(roomDB, retrofit.create(MenuService::class.java))
            randomMenuRepository = RandomMenuRepository(roomDB, retrofit.create(MenuService::class.java))
            listMenuDietRepository = ListMenuDietRepository(roomDB, retrofit.create(MenuService::class.java))
            menuDietDataRepository = MenuDietDataRepository(roomDB, retrofit.create(MenuService::class.java))
        }
    }
}