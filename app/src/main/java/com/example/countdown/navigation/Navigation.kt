package com.example.countdown.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countdown.screens.Countdown
import com.example.countdown.screens.CountdownViewModel
import com.example.countdown.screens.CountdownsList

@ExperimentalComposeUiApi
@Composable
fun Navigation(countdownViewModel: CountdownViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.CountdownList.name
    ) {
        composable(Routes.CountdownList.name) {
            CountdownsList(countdownViewModel, navController)
        }

        composable(Routes.Countdown.name){
            Countdown(countdownViewModel,navController)
        }
    }
}