package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androiddevs.mvvmnewsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Articledatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticlesDao

    companion object {
        @Volatile
        private var instance: Articledatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDabase(context).also { instance = it }
        }

        private fun createDabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                Articledatabase::class.java,
                "article_db_db"
            ).build()
    }
}