package com.example.countdown.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Countdown::class], version = 1, exportSchema = false)
@TypeConverters()
abstract class CountdownDatabase:RoomDatabase() {
    abstract fun countdownDao():CountdownDatabaseDao
}