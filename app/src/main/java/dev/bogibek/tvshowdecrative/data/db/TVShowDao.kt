package dev.bogibek.tvshowdecrative.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.bogibek.tvshowdecrative.model.TVShow

@Dao
interface TVShowDao {

    @Query("SELECT * FROM tv_show")
    suspend fun getTVShowsFromDB(): List<TVShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShowToDB(tvShow: TVShow)

    @Query("DELETE FROM tv_show")
    suspend fun deleteTvShowsFromDB()
}