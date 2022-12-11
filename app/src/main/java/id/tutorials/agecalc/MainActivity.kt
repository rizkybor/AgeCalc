package id.tutorials.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvAgeInHours : TextView? = null
    private var tvageInDays : TextView? = null
    private var tvAgeInCalendarYears : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        // val btnCallBor : Button = findViewById(R.id.btnCallBor)

//        btnCallBor.setOnClickListener {
//            Toast.makeText(this, "Hallobor", Toast.LENGTH_LONG).show()
//        }

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)
        tvageInDays = findViewById(R.id.tvAgeInDays)
        tvAgeInCalendarYears = findViewById(R.id.tvAgeInCalendarYears)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    // buat function clickDate Picker
    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
        {
            _, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(
                this,
                "Text was $selectedYear, month was ${selectedMonth+1}, day of month was $selectedDayOfMonth",
                Toast.LENGTH_LONG).show()

            // change value tvSelectedDate
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate?.text = selectedDate

            // format tanggal
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            // mengambil tanggal yang di pilih untuk menghitung berapa menit kita hidup saat lahir
            val theDate = sdf.parse(selectedDate)

            theDate ?.let{
                val selectedDateInMinutes = theDate.time / 60000
                // Current Date dibuat untuk mengambil value tanggal saat ini
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate ?.let {
                    val currentDateInMinutes = currentDate.time / 60000
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                    val differenceInHours = differenceInMinutes / 60
                    val differenceInDays = differenceInHours / 24
                    val differenceInCalendarYears = differenceInDays / 365
                    tvAgeInMinutes?.text = differenceInMinutes.toString() + " minutes"
                    tvAgeInHours?.text = differenceInHours.toString() + " hours"
                    tvageInDays?.text = differenceInDays.toString() + " days"
                    tvAgeInCalendarYears?.text = differenceInCalendarYears.toString() + " years"
                }
            }
        },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

        dpd.show()
        println("okayy")
    }
}