package id.ac.istts.myfit.Data.Source.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.istts.myfit.Data.MenuDietData

@Dao
interface MenuDietDataDao {
    @Query("select * from menudietdata")
    fun getAll(): List<MenuDietData>
    @Query("delete from menudietdata")
    fun clearDb()

    @Query("DELETE FROM menudietdata WHERE id IN (SELECT id FROM menudietdata GROUP BY name HAVING COUNT(*) > 1)")
    fun deleteDuplicates()
    @Insert
    fun insertMany(menus:List<MenuDietData>)
}