package com.example.countdown.components

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun TimePicker(context: Context, initHour: Int, initMinute: Int, teste:Unit?) {
    val time = remember { mutableStateOf("") }
//    val timePickerDialog = TimePickerDialog(
//        context,
//        {_, hour : Int, minute: Int ->
//            time.value = "$hour:$minute"
//        }, initHour, initMinute, true
//    )
//    teste

//    Button(onClick = {
//        timePickerDialog.show()
//    }) {
//        Text(text = "Open Time Picker")
//    }
}