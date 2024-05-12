package id.ac.istts.myfit.Data.Source.Local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import id.ac.istts.myfit.Data.AllMenuUser
import id.ac.istts.myfit.Data.Menu

@Dao
interface AllMenuUserDao {
    @Query("select * from menus")
    fun getAll(): List<Menu>

    @Query("select * from menus order by id desc")
    fun sortRecent(): List<Menu>

//    @Query("select * from menus order by like asc")
//    fun sortLike(): List<Menu>

    @Query("select * from menus where id=:id")
    fun getById(id:Int): Menu

    @Insert
    fun insert(menu: Menu)

    @Update
    fun update(menu: Menu)

    @Delete
    fun delete(menu: Menu)

    @Query("delete from menus")
    fun clearDb()

    @Insert
    fun insertMany(menus:List<Menu>)
}