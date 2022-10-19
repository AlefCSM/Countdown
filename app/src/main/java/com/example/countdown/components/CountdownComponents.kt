package com.example.countdown.components

import android.content.Context
import android.system.Os.accept
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.countdown.R
import com.example.countdown.model.Countdown
import kotlin.math.roundToInt

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
//    countdown: Countdown = Countdown(title = "teste", description = "", limitDate = ""),
    onClick: () -> Unit
) {

    var backgroundColor by remember { mutableStateOf(Color.Cyan) }
    val color = animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(3000)
    )

    val localDensity = LocalDensity.current
    val boxSize = 60.dp
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val pxSize = with(localDensity) { boxSize.toPx() }
    val anchors = mapOf(0f to 0, -pxSize to 1)


    val icon = Icons.Default.Delete

    val scale by animateFloatAsState(
        if (swipeableState.offset.value.roundToInt() == 0) 0.75f else 1.2f
    )


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
                .fillMaxSize()
//                .background(Color.Gray)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .background(Color(0xFFFB0A00).copy(alpha = 0.2f))
//                    .clip(RoundedCornerShape(20.dp))
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
                            println("offset:" + swipeableState.offset.value.roundToInt())
                            println("targetValue:" + swipeableState.targetValue)
                            println("currentValue:" + swipeableState.currentValue)
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
//                            CountdownCard(countdown = countdown)
                        }
                    }
                }
            }
        }
    }
}