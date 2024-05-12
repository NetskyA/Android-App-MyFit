package id.ac.istts.myfit.Data

import android.util.Log
import id.ac.istts.myfit.Data.Source.Local.AppDatabase
import id.ac.istts.myfit.Data.Source.Remote.MenuService

class AllMenuUserRepository (
//    local yang db room
    private val localDataSource: AppDatabase,
//    remote yang api
    private val remoteDataSource:MenuService
) {
    suspend fun getAllMenus(id: Int): ArrayList<Menu> {
//        var posts:AllMenuUser = remoteDataSource.getAllMenuUser(id)
//        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAA", posts.allMenuUser.toString())
//        var tempPost = ArrayList(posts.allMenuUser)
//        localDataSource.allMenuUserDao().clearDb()
//        localDataSource.allMenuUserDao().insertMany(tempPost)
//        return ArrayList(localDataSource.allMenuUserDao().getAll())
        return arrayListOf()
    }
    suspend fun getMenuById(id:Int):Menu {
        return localDataSource.allMenuUserDao().getById(id)
    }
//    suspend fun createMenu(menu:TempMenu) {
//        val newPost = remoteDataSource.uploadMenu(menu) //insert remote dl
//        localDataSource.allMenuUserDao().insert(menu)
//    }
//    suspend fun updateMenu(menu:Menu) {
//        remoteDataSource.update(menu.id, menu)
//        localDataSource.allMenuUserDao().update(menu)
//    }
//    suspend fun deleteMenu(menu:Menu) {
//        remoteDataSource.delete(menu.id)
//        localDataSource.allMenuUserDao().update(menu)
//    }
}