package id.ac.istts.myfit.Data.Source.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.istts.myfit.Data.Menu
import id.ac.istts.myfit.Data.Post
import id.ac.istts.myfit.Data.User

@Database(entities = [Post::class, User::class, Menu::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun allMenuUserDao(): AllMenuUserDao
}