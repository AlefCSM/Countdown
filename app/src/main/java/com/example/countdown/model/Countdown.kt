package com.example.countdown.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "countdown_tb")
data class Countdown(
    @PrimaryKey
    @NonNull
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "limit_date")
    val limitDate: String
)