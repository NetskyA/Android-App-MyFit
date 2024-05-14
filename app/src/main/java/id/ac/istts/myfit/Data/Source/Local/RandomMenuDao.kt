package id.ac.istts.myfit.Data.Source.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.RandomMenu
import id.ac.istts.myfit.Data.RandomMenuData

@Dao
interface RandomMenuDao {
    @Query("select * from randomMenus")
    fun getAll(): List<RandomMenu>
    @Query("delete from randomMenus")
    fun clearDb()

    @Insert
    fun insertMany(randomMenus:List<RandomMenu>)
}