package id.ac.istts.myfit.Data.Source.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.istts.myfit.Data.ListMenuDiet
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.MenuDietData
import id.ac.istts.myfit.Data.Post
import id.ac.istts.myfit.Data.RandomMenu
import id.ac.istts.myfit.Data.User

@Database(entities = [Post::class, User::class, Menu::class, RandomMenu::class,ListMenuDiet::class,MenuDietData::class], version = 7)
abstract class AppDatabase: RoomDatabase() {
    abstract fun allMenuUserDao(): AllMenuUserDao
    abstract fun randomMenuDao(): RandomMenuDao
    abstract fun menuDietDao(): MenuDietDao
    abstract fun menuDietDataDao(): MenuDietDataDao
}