package com.example.countdown.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.countdown.model.Countdown

@Composable
fun CountdownCard(countdown: Countdown) {

    @Composable
    fun YearCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0")
                Text(text = "Years")
            }
        }

    }

    @Composable
    fun MonthCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0")
                Text(text = "Months")
            }
        }
    }

    @Composable
    fun DayCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0")
                Text(text = "Days")
            }
        }
    }

    @Composable
    fun HourCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0")
                Text(text = "Hours")
            }
        }
    }

    @Composable
    fun MinuteCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0")
                Text(text = "Minutes")
            }
        }
    }

    @Composable
    fun SecondCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "0")
                Text(text = "Seconds")
            }
        }
    }

    Surface(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = countdown.title)
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                YearCounter()
                MonthCounter()
                DayCounter()
                HourCounter()
                MinuteCounter()
                SecondCounter()
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    label: String?,
    value:String,
    maxLines:Int = 1,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
) {
    OutlinedTextField(
        modifier = modifier,
        enabled = enabled,
        value = value,
        maxLines = maxLines,
        onValueChange = onValueChange,
        label = { label?.let { Text(it) } })
}