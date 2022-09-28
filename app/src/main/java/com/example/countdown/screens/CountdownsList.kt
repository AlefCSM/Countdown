package com.example.countdown.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.countdown.components.CountdownCard
import com.example.countdown.model.Countdown

@Preview(showBackground = true)
@Composable
fun CountdownsList(){
    Surface() {
Box{
    Column{
        CountdownCard()
        CountdownCard()
        CountdownCard()
    }
    FloatingActionButton(modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp),
        onClick = { }) {
        Text(text = "+")
    }

}


    }
}