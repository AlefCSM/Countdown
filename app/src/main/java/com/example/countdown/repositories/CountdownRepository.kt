package com.example.countdown.repositories

import com.example.countdown.model.Countdown
import com.example.countdown.model.CountdownDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CountdownRepository @Inject constructor(private val countdownDatabaseDao: CountdownDatabaseDao):CountdownRepositoryInterface {
    override suspend fun addCountdown(countdown: Countdown) = countdownDatabaseDao.insert(countdown)
    override suspend fun updateCountdown(countdown: Countdown) = countdownDatabaseDao.update(countdown)
    override suspend fun deleteCountdown(countdown: Countdown) = countdownDatabaseDao.delete(countdown)
    override fun getAllCountdowns(): Flow<List<Countdown>> =
        countdownDatabaseDao.getCountdowns().flowOn(Dispatchers.IO).conflate()
}