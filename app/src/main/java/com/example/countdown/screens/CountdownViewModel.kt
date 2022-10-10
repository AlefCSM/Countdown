package com.example.countdown.screens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countdown.model.Countdown
import com.example.countdown.repositories.CountdownRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CountdownViewModel @Inject constructor(
    private val repository: CountdownRepository
) :
    ViewModel() {

    var title :MutableLiveData<String> = MutableLiveData("")
    var description:MutableLiveData<String> = MutableLiveData("")
    var date:MutableLiveData<String> = MutableLiveData("")
    val time:MutableLiveData<String> = MutableLiveData("")

    lateinit var currentCountdown: Countdown

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

    fun remove(index:Int){

    }

    fun addCountdown(countdown: Countdown) =
        viewModelScope.launch { repository.addCountdown(countdown) }

    fun updateCountdown(countdown: Countdown) =
        viewModelScope.launch { repository.updateCountdown(countdown) }

    fun removeCountdown(countdown: Countdown) =
        viewModelScope.launch { repository.deleteCountdown(countdown) }

    fun clearCurrentCountdown() {
        currentCountdown = Countdown(title = "", description = "", limitDate = "")
    }

    fun validate(date:String,time:String,title:String,description:String):Boolean {
        if (date.isEmpty() || time.isEmpty()) {
            return false
        }

        return validateDateTime(date, time)
    }

    private fun validateDateTime(date:String,time: String):Boolean{
        val d1 = "$date $time"

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        val currentDate = LocalDate.now()

        val selectedDate: LocalDate = LocalDate.parse(d1, formatter)

        return when {
            selectedDate.isAfter(currentDate) -> true
            else -> false
        }
    }
}