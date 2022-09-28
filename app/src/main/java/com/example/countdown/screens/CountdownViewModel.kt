package com.example.countdown.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countdown.model.Countdown
import com.example.countdown.repositories.CountdownRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountdownViewModel @Inject constructor(private val repository: CountdownRepositoryInterface) :
    ViewModel() {
    private val _countdownList = MutableStateFlow<List<Countdown>>(emptyList())
    val countdownList = _countdownList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCountdowns().distinctUntilChanged().collect { listOfCountdowns ->
                if (listOfCountdowns.isEmpty()) {
                    Log.d("Empty", "Empty list")
                } else {
                    _countdownList.value = listOfCountdowns
                }

            }
        }
    }

    fun addCountdown(countdown: Countdown) =
        viewModelScope.launch { repository.addCountdown(countdown) }

    fun updateCountdown(countdown: Countdown) =
        viewModelScope.launch { repository.updateCountdown(countdown) }

    fun removeCountdown(countdown: Countdown) =
        viewModelScope.launch { repository.deleteCountdown(countdown) }
}