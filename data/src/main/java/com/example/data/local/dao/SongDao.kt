package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.model.SongEntity

@Dao
interface SongDao {

  @Query("SELECT * FROM SongEntity WHERE lower(term)=lower(:keyword)")
  suspend fun getSongs(keyword: String): List<SongEntity>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertSong(songEntity: SongEntity)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertSongList(list: List<SongEntity>)
}