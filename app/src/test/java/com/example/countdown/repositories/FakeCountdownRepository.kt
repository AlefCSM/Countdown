//package com.example.countdown.repositories
//
//import com.example.countdown.model.Countdown
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//
//class FakeCountdownRepository : CountdownRepositoryInterface {
//
//    private val _countdownList = MutableStateFlow<List<Countdown>>(emptyList())
//    val countdownList = _countdownList.asStateFlow()
//
//    override suspend fun addCountdown(countdown: Countdown) {
//        _countdownList.value = _countdownList.value.toMutableList() + countdown
//    }
//
//    override suspend fun updateCountdown(countdown: Countdown) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteCountdown(countdown: Countdown) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAllCountdowns(): Flow<List<Countdown>> {
//        TODO("Not yet implemented")
//    }
//}