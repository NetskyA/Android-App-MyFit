package id.ac.istts.myfit.Data

import android.util.Log
import id.ac.istts.myfit.Data.Source.Local.AppDatabase
import id.ac.istts.myfit.Data.Source.Remote.MenuService

class MenuDietDataRepository(private val localDataSource: AppDatabase,
                             private val remoteDataSource: MenuService
) {
    suspend fun getAllMenuDietData(id: String): MutableList<MenuDietData> {
        try {
            val menus: List<MenuDietData> = remoteDataSource.getMenuById(id)
            localDataSource.menuDietDataDao().clearDb()
            localDataSource.menuDietDataDao().insertMany(menus)
            localDataSource.menuDietDataDao().deleteDuplicates()
            return localDataSource.menuDietDataDao().getAll().toMutableList()
        } catch (e: Exception) {
            return localDataSource.menuDietDataDao().getAll().toMutableList()
        }
    }

    suspend fun saveAllMenuDietData(menus: List<MenuDietData>) {
        Log.e("saveAllMenuDietData",menus.toString() )
        localDataSource.menuDietDataDao().clearDb()
        localDataSource.menuDietDataDao().insertMany(menus)
        localDataSource.menuDietDataDao().deleteDuplicates()
    }
}