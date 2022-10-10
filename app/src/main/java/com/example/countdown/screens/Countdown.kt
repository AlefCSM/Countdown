package com.example.countdown.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countdown.components.CustomTextField
import com.example.countdown.model.Countdown
import java.util.*

//@Preview(showBackground = true)
@Composable
fun Countdown(
    countdownViewModel: CountdownViewModel,
    navController: NavController
//    update: Boolean
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf("")
    }

    var time by remember {
        mutableStateOf("")
    }

    fun timeDialogBuilder(context: Context): TimePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            val sHour = hour.toString().padStart(2, '0')
            val sMinute = minute.toString().padStart(2, '0')
            time = "$sHour:$sMinute"
        }, 0, 0, true
    )

    fun dateDialogBuilder(context: Context): DatePickerDialog {
        val mYear: Int
        val mMonth: Int
        val mDay: Int
        val mCalendar = Calendar.getInstance()

        mYear = mCalendar.get(Calendar.YEAR)
        mMonth = mCalendar.get(Calendar.MONTH)
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, day: Int ->
                val sDay = day.toString().padStart(2, '0')
                val sMonth = (month + 1).toString().padStart(2, '0')
                date = "$sDay/$sMonth/$year"
            }, mYear, mMonth, mDay)
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000

        return datePickerDialog
    }

    val context = LocalContext.current
    val timePickerDialog = timeDialogBuilder(context)
    val datePickerDialog = dateDialogBuilder(context)


    fun save() {
        if (countdownViewModel.validate(date, time, title, description)) {
            val countdown =
                Countdown(title = title, description = description, limitDate = "$date $time")
            countdownViewModel.addCountdown(countdown)
            navController.popBackStack()
        }
//        navController.popBackStack()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(text = "Set a date and time")
//
//
//
//
//            TimePicker(LocalContext.current, 0, 0, teste =

            CustomTextField(
                label = "Date",
                value = date,
                modifier = Modifier.clickable { datePickerDialog.show() },
                enabled = false,
                onValueChange = {
                    date = it
                }
            )
            CustomTextField(
                label = "Time", value = time,
                modifier = Modifier.clickable { timePickerDialog.show() },
                enabled = false,
                onValueChange = {})
            CustomTextField(
                label = "Title",
                value = title,
                onValueChange = { title = it })
            CustomTextField(
                label = "Description (Optional)",
                value = description,
                onValueChange = { description = it })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                save()
            }) {
                Text(text = "Save")
            }
        }
    }
}