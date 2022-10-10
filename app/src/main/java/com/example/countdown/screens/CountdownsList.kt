package com.example.countdown.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countdown.components.CountdownCard
import com.example.countdown.navigation.Routes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountdownsList(countdownViewModel: CountdownViewModel, navController: NavController) {

    val countdownList = countdownViewModel.countdownList.value

    Surface(modifier = Modifier.fillMaxSize()) {
        Box {
            LazyColumn {
                items(countdownList.size) { countdown ->
                    val dismissState = rememberDismissState(confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            //countdownViewModel.removeCountdown(countdownList[countdown])
                        }
                        true
                    })

                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
//                        dismissThresholds = { direction ->
//                            FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
//                        },
                        background = {
                            val color by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.Default -> Color.White
                                    else -> Color.Red
                                }
                            )
                            val alignment = Alignment.CenterEnd
                            val icon = Icons.Default.Delete

                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1.2f
                            )
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = Dp(20f))
                                    ,
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Delete Icon",
                                    modifier = Modifier.scale(scale)
                                )
                            }
                        },
                        dismissContent = {
                            CountdownCard(countdown = countdownViewModel.countdownList.value[countdown])
                        })
                    Divider(Modifier.fillMaxWidth(), Color.DarkGray)

                }
            }
            FloatingActionButton(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
                onClick = { navController.navigate(Routes.Countdown.name) }) {
                Text(text = "+")
            }
        }
    }
}