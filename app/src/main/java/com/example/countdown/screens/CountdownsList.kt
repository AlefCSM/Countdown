package com.example.countdown.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countdown.components.CountdownCard
import com.example.countdown.components.SwipeToReveal
import com.example.countdown.model.Countdown
import com.example.countdown.navigation.Routes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountdownsList(countdownViewModel: CountdownViewModel, navController: NavController) {

    val openDialog = remember { mutableStateOf(false) }
    lateinit var countdownToDelete: Countdown
    val countdownList = countdownViewModel.countdownList.collectAsState().value
    val deletedItems = remember {
        mutableListOf<Countdown>()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Alert")
                },
                text = {
                    Text(text = "Do you really want to delete?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            countdownViewModel.removeCountdown(countdownToDelete)
                            deletedItems.add(countdownToDelete)
                            openDialog.value = false
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }

        Box {

            if (countdownList.isNotEmpty()) {
                LazyColumn {


                    items(countdownList.size) { countdown ->

                        AnimatedVisibility(
                            visible = !deletedItems.contains(countdownList[countdown]),
                            enter = expandVertically(),
                            exit = shrinkVertically(),
                            modifier = Modifier.clickable { navController.navigate(Routes.Countdown.name) }
                        ) {
                            SwipeToReveal(composable = {
                                CountdownCard(countdown = countdownList[countdown])
                            },
                                onClick = {
                                    openDialog.value = true
                                    countdownToDelete =
                                        countdownList[countdown]

                                }
                            )
                        }


                        Divider(Modifier.fillMaxWidth(), Color.LightGray)
                    }
                }
            } else {
                Text(text = "Empty list")
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