package com.example.countdown.components

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.countdown.model.Countdown
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@Composable
fun CountdownCard(countdown: Countdown) {


    val seconds: MutableState<Long> = remember {
        mutableStateOf(0)
    }
    val minutes: MutableState<Long> = remember {
        mutableStateOf(0)
    }
    var days: MutableState<Long> = remember {
        mutableStateOf(0)
    }
    val hours: MutableState<Long> = remember {
        mutableStateOf(0)
    }
    val months: MutableState<Long> = remember {
        mutableStateOf(0)
    }
    val years: MutableState<Long> = remember {
        mutableStateOf(0)
    }


    val currentTime = Calendar.getInstance().time
    val format = SimpleDateFormat("dd/MM/yyyy hh:mm",Locale.getDefault())
    val limitDate = format.parse(countdown.limitDate)

    val difference = limitDate.time - currentTime.time

    val secondsTimer = object : CountDownTimer(difference, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            var diff = millisUntilFinished
            val secondsInMilli: Long = 1000
            val minutesInMilli = secondsInMilli * 60
            val hoursInMilli = minutesInMilli * 60
            val daysInMilli = hoursInMilli * 24

            days.value = diff/daysInMilli
            diff %= daysInMilli

            hours.value = diff/hoursInMilli
            diff %= hoursInMilli

            minutes.value = diff/minutesInMilli
            diff %= minutesInMilli

            seconds.value = diff/secondsInMilli
        }

        override fun onFinish() {

        }

    }

    secondsTimer.start()
    @Composable
    fun YearCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${years.value}")
                Text(text = "Years")
            }
        }

    }

    @Composable
    fun MonthCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${months.value}")
                Text(text = "Months")
            }
        }
    }

    @Composable
    fun DayCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${days.value}")
                Text(text = "Days")
            }
        }
    }

    @Composable
    fun HourCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${hours.value}")
                Text(text = "Hours")
            }
        }
    }

    @Composable
    fun MinuteCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${minutes.value}")
                Text(text = "Minutes")
            }
        }
    }

    @Composable
    fun SecondCounter() {
        Surface(modifier = Modifier.padding(horizontal = 4.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${seconds.value}")
                Text(text = "Seconds")
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
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
    value: String,
    maxLines: Int = 1,
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

//@Preview(showBackground = true)
@ExperimentalMaterialApi
@Composable
fun SwipeToReveal(
    composable: @Composable () -> Unit,
    onClick: () -> Unit
) {

    val localDensity = LocalDensity.current
    val boxSize = 60.dp
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val pxSize = with(localDensity) { boxSize.toPx() }
    val anchors = mapOf(0f to 0, -pxSize to 1)

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .background(Color(0xFFFB0A00).copy(alpha = 0.2f))
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
//                        thresholds = { _, _ ->
//                            FractionalThreshold(0.3f)
//                        },
                        orientation = Orientation.Horizontal
                    )
            ) {
                IconButton(
                    onClick = {
                        onClick()
                    },
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterEnd)


                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete Icon",
                        tint = Color.Red
//                                    modifier = Modifier.scale(scale)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset {
                            IntOffset(swipeableState.offset.value.roundToInt(), 0)
                        }
//                        .fillMaxHeight()
                        .align(
                            Alignment.Center
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Row() {
                            composable()
                        }
                    }
                }
            }
        }
    }
}