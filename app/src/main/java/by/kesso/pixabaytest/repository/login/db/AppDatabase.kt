package by.kesso.pixabaytest.repository.login.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.kesso.pixabaytest.repository.login.entiry.User
import by.kesso.pixabaytest.repository.login.entiry.UserDao


@Database(
    version = 1,
    exportSchema = true,
    entities = [
        User::class
    ]
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        private var DB_NAME = "gallery"

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        protected fun buildDatabase(context: Context): AppDataBase {
            return Room
                .databaseBuilder(context.applicationContext, AppDataBase::class.java, DB_NAME)
                .addMigrations(

                )
                .build()
        }

    }
}