package com.example.countdown.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CountdownDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countdown: Countdown)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(countdown: Countdown)

    @Delete
    suspend fun delete(countdown: Countdown)

    @Query("SELECT * FROM countdown_tb")
    fun getCountdowns(): Flow<List<Countdown>>

    @Query("SELECT * FROM countdown_tb WHERE id = :id")
    suspend fun getCountdownById(id:String):Countdown
}