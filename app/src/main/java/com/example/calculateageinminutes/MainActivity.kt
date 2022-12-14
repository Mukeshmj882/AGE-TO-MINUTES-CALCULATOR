package com.example.calculateageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)


        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }

    }


    fun clickDatePicker(view: View) {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
//                Toast.makeText(
//                    this,
//                    "The Chosen year is $selectedYear,the Month is $selectedMonth and the day is $selectedDayOfMonth",
//                    Toast.LENGTH_LONG
//                ).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                val tvSelectedDateInSeconds = findViewById<TextView>(R.id.tvSelectedDateInSeconds)

                tvSelectedDate.setText(selectedDate)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())


                val selectedDateInSeconds = theDate!!.time / 1000
                val currentDateToSeconds = currentDate!!.time / 1000
                val differenceInSeconds = currentDateToSeconds - selectedDateInSeconds

                tvSelectedDateInSeconds.setText(differenceInSeconds.toString())


            }, year, month, day
        )

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }

}