package com.example.countdown.repositories

import com.example.countdown.model.Countdown
import kotlinx.coroutines.flow.Flow

class FakeCountdownRepository:CountdownRepositoryInterface {
    override suspend fun addCountdown(countdown: Countdown) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCountdown(countdown: Countdown) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCountdown(countdown: Countdown) {
        TODO("Not yet implemented")
    }

    override fun getAllCountdowns(): Flow<List<Countdown>> {
        TODO("Not yet implemented")
    }
}