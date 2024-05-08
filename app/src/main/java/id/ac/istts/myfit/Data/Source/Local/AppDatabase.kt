package id.ac.istts.myfit.Data.Source.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.istts.myfit.Data.Post
import id.ac.istts.myfit.Data.User

@Database(entities = [Post::class, User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

}