package com.example.warrungu.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DictionaryItem::class],
    version = 1
)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract val dao: DictionaryItemDao

    companion object{
        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        fun getInstance(context: Context): DictionaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDatabase::class.java,
                    "my_dbbswxghx"
                )
                    .createFromAsset("db/warrungu.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}