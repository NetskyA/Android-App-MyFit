package id.ac.istts.myfit.Data.Source.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.istts.myfit.Data.ListMenuDiet

@Dao
interface MenuDietDao {
    @Query("select * from listmenudiet")
    fun getAll(): List<ListMenuDiet>
    @Query("delete from listmenudiet")
    fun clearDb()
    @Insert
    fun insertMany(randomMenus:List<ListMenuDiet>)
}