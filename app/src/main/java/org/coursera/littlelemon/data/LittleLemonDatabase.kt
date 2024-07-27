package org.coursera.littlelemon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.coursera.littlelemon.data.menu.MenuItemDao
import org.coursera.littlelemon.data.menu.MenuItem

@Database(entities = [MenuItem::class], version = 1, exportSchema = false)
abstract class LittleLemonDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        fun getDatabase(applicationContext: Context): LittleLemonDatabase {
            return Room.databaseBuilder(applicationContext,
                                        LittleLemonDatabase::class.java,
                                        "little-lemon-database").build()
        }
    }
}