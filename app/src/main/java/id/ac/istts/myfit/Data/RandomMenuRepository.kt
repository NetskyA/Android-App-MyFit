package id.ac.istts.myfit.Data

import id.ac.istts.myfit.Data.Source.Local.AppDatabase
import id.ac.istts.myfit.Data.Source.Remote.MenuService

class RandomMenuRepository (
//    local yang db room
    private val localDataSource: AppDatabase,
//    remote yang api
    private val remoteDataSource: MenuService
) {
    suspend fun getAllMenus(id: Int): ArrayList<RandomMenu> {
        try {
            var posts: RandomMenuData = remoteDataSource.getRandomMenu(id)
            var tempPost = ArrayList(posts.allRandomMenu)
//        localDataSource.randomMenuDao().clearDb()
            localDataSource.randomMenuDao().insertMany(tempPost)
//        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAA", ArrayList(localDataSource.allMenuUserDao().getAll()).toString())
            return ArrayList(localDataSource.randomMenuDao().getAll())
        }catch (e:Exception){
            return ArrayList(localDataSource.randomMenuDao().getAll())
        }
//        return arrayListOf()
    }
    suspend fun clearDataMenus(){
        localDataSource.randomMenuDao().clearDb()
    }
}