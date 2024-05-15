package id.ac.istts.myfit.Data

import id.ac.istts.myfit.Data.Source.Local.AppDatabase
import id.ac.istts.myfit.Data.Source.Remote.MenuService

class ListMenuDietRepository(private val localDataSource: AppDatabase,
                             private val remoteDataSource: MenuService) {
    suspend fun getAllMenuDiet(id: Int): MutableList<ListMenuDiet> {
        try {
            val menus: List<ListMenuDiet> = remoteDataSource.getMenuDiet(id)
            localDataSource.menuDietDao().clearDb()
            localDataSource.menuDietDao().insertMany(menus)
            return localDataSource.menuDietDao().getAll().toMutableList()
        } catch (e: Exception) {
            return localDataSource.menuDietDao().getAll().toMutableList()
        }
    }

    suspend fun saveAllMenuDiet(menus: MutableList<ListMenuDiet>) {
        remoteDataSource.saveMenuDiet(menus)
        localDataSource.menuDietDao().clearDb()
        localDataSource.menuDietDao().insertMany(menus)
    }
}