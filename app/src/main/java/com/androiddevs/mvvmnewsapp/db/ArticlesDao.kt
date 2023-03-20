package com.androiddevs.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.mvvmnewsapp.models.Article


@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM article")
    fun getAllArticle(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}