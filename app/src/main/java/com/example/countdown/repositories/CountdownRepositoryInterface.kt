package com.example.countdown.repositories

import com.example.countdown.model.Countdown
import kotlinx.coroutines.flow.Flow


interface CountdownRepositoryInterface {
    suspend fun addCountdown(countdown: Countdown)
    suspend fun updateCountdown(countdown: Countdown)
    suspend fun deleteCountdown(countdown: Countdown)
    fun getAllCountdowns(): Flow<List<Countdown>>
}