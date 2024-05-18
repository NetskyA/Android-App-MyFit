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
        try{
            var posts:AllMenuUser = remoteDataSource.getAllMenuUser(id)
            var tempPost = ArrayList(posts.allMenuUser)
            localDataSource.allMenuUserDao().clearDb()
            localDataSource.allMenuUserDao().insertMany(tempPost)
//        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAA", ArrayList(localDataSource.allMenuUserDao().getAll()).toString())
            return ArrayList(localDataSource.allMenuUserDao().getAll())
        }catch (e:Exception){
            return ArrayList(localDataSource.allMenuUserDao().getAll())
        }
//        return arrayListOf()
    }
    suspend fun getMenuById(id:Int):Menu {
        return localDataSource.allMenuUserDao().getById(id)
    }

    suspend fun sortOldest(): ArrayList<Menu> {
        return ArrayList(localDataSource.allMenuUserDao().sortOldest())
    }
    suspend fun sortFavorite(): ArrayList<Menu> {
        return ArrayList(localDataSource.allMenuUserDao().sortLike())
    }
    suspend fun reset(): ArrayList<Menu> {
        return ArrayList(localDataSource.allMenuUserDao().getAll())
    }
//    suspend fun createMenu(menu:TempMenu) {
//        val newPost = remoteDataSource.uploadMenu(menu) //insert remote dl
//        localDataSource.allMenuUserDao().insert(menu)
//    }
//    suspend fun updateMenu(menu:Menu) {
//        remoteDataSource.update(menu.id, menu)
//        localDataSource.allMenuUserDao().update(menu)
//    }
    suspend fun deleteMenu(menu:Menu):String {
        var result = remoteDataSource.deleteMenuById(menu.id.toString())
        localDataSource.allMenuUserDao().delete(menu)
        return result.text
    }
}