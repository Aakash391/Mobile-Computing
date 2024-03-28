package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.ui.theme.WeatherAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Homescreen()
                }
            }
        }
    }
}

fun compareDates(date: String, currentDate: String): Int {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val parsedDate = dateFormat.parse(date)
    val parsedCurrentDate = dateFormat.parse(currentDate)

    return parsedDate.compareTo(parsedCurrentDate)
}

@Composable
fun Homescreen(modifier: Modifier= Modifier){
    val image = painterResource(id = R.drawable.pxfuel_1_)
    Box(modifier = modifier.fillMaxSize()){
        Image(painter = image,

            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier.matchParentSize()
        )
    }


    MyDatePickerDialog(LocalContext.current)
//    printTemp("21.9","12.6")
}
//@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(context: Context,modifier:Modifier= Modifier) {
    var datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    var date by remember {
        mutableStateOf("")
    }
    var max by remember {
        mutableStateOf(0F)
    }
    var min by remember {
        mutableStateOf(0F)
    }
    var fetched by remember {
        mutableStateOf(false)
    }
    var datePicked by remember {
        mutableStateOf(false)
    }
    var isFuture by remember {
        mutableStateOf(false)
    }
    var dataAvail by remember {
        mutableStateOf(true)
    }

    var connected by remember {
        mutableStateOf(false)
    }

    fun checkNetworkConnectivity() {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.allNetworkInfo
        for (ni in netInfo) {
            if (ni.isConnected) {
                connected = true
                break
            } else {
                connected = false
            }
        }
    }

    checkNetworkConnectivity()
    LaunchedEffect(Unit) {
        while (true) {
            checkNetworkConnectivity()
            delay(1000)
        }
    }

    var database: Database = Database(context)
    var inserted by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    var status by remember {
        mutableStateOf("Online")
    }

    val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time)
    var futureYear by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .height(500.dp)
            .width(330.dp)
            .padding(17.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

    ) {


        Spacer(modifier = modifier.height(50.dp))
        if(connected){
            if(status=="Offline"){
                fetched=false
            }
            status="Online"

        }
        else{
            status="Offline"
        }
        if(status=="Online") {
            Row(){
                Text(
                    text = "Connection Status: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = status,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green
                )
            }

        }
        else{
            Row(){
                Text(
                    text = "Connection Status: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2dda86)
                )

                Text(
                    text = status,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }
        }

        Spacer(modifier = modifier.height(50.dp))
        Button(
            onClick = {
                showDatePicker = true
                fetched=false
            },
            modifier = modifier,
        ) {
            Text(text = "Pick Date")
        }
        if (showDatePicker) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                DatePickerDialog(
                    onDismissRequest = {  },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                var selectedDate = Calendar.getInstance().apply {
                                    timeInMillis = datePickerState.selectedDateMillis!!
                                }
                                date = SimpleDateFormat("yyyy-MM-dd").format(selectedDate.time)
                                if(compareDates(date,currentDate)>0){
                                    Log.d("Future Date","True")
                                    isFuture=true
                                }
                                showDatePicker = false
                                datePicked = true
                            }
                        ) { Text("OK") }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showDatePicker = false
                            }
                        ) { Text("Cancel") }
                    }
                ) {
                    DatePicker(
                        state = datePickerState,
                        modifier = Modifier,
                        showModeToggle = false
                    )
                }
            }
        }

        Spacer(modifier = modifier.height(30.dp))

        Button(
            onClick = {
                if(datePicked==true) {
                    if (compareDates(date, currentDate) > 0) {
                        var year = date.split("-")[0].toInt()
                        var currentyear = currentDate.split("-")[0].toInt()

                        if(year>currentyear){
                            futureYear=true
                        }
                        else{
                            futureYear=false
                        }
                        isFuture = true
                    } else {
                        isFuture = false
                    }

                    scope.launch {

                        if (date != "" && connected == true) {
                            if(futureYear==true){
                                var year = date.split("-")[0]
                                var currentyear = currentDate.split("-")[0]
                                var month = date.split("-")[1]
                                var dte = date.split("-")[2]
                                var newDate = currentyear + "-" + month + "-" + dte

                                var response = fetchTemperature(newDate)

                                if (response.body().toString() == "") {
                                    Log.d("Empty string", "")
                                } else {

                                    //Convert to Celsius
                                    max = response.body()!!.days[0].tempmax.toFloat()
                                    min = response.body()!!.days[0].tempmin.toFloat()
                                    max = ((max - 32) * (5 / 9.0)).toBigDecimal()
                                        .setScale(1, RoundingMode.CEILING).toFloat()
                                    min = ((min - 32) * (5 / 9.0)).toBigDecimal()
                                        .setScale(1, RoundingMode.CEILING).toFloat()
                                    database.addtemp(
                                        date = newDate,
                                        maxtemp = max.toString(),
                                        mintemp = min.toString()
                                    )

                                    var year = newDate.split("-")[0].toInt()
                                    var month = newDate.split("-")[1]
                                    var dte = newDate.split("-")[2]

                                    Log.d("Year", year.toString())
                                    Log.d("Month", month.toString())
                                    Log.d("dte", dte.toString())

//                              Inserting and fetching previous 10 year dates

                                    for (i in 1..10) {
                                        year = year - 1
                                        var newDate1 =
                                            year.toString() + "-" + month + "-" + dte
                                        Log.d("NewDate", newDate1)
                                        var response = fetchTemperature(newDate1)
                                        if (response.body().toString() != "") {
                                            var newmax = response.body()!!.days[0].tempmax.toFloat()
                                            var newmin = response.body()!!.days[0].tempmin.toFloat()
                                            newmax = ((newmax - 32) * (5 / 9.0)).toBigDecimal()
                                                .setScale(1, RoundingMode.CEILING).toFloat()
                                            newmin = ((newmin - 32) * (5 / 9.0)).toBigDecimal()
                                                .setScale(1, RoundingMode.CEILING).toFloat()

                                            database.addtemp(
                                                date = newDate1,
                                                maxtemp = newmax.toString(),
                                                mintemp = newmin.toString()
                                            )
                                        }
                                    }

                                }
                            }
                            else {
                                var response = fetchTemperature(date)

                                if (response.body().toString() == "") {
                                    Log.d("Empty string", "")
                                } else {

                                    //Convert to Celsius
                                    max = response.body()!!.days[0].tempmax.toFloat()
                                    min = response.body()!!.days[0].tempmin.toFloat()
                                    max = ((max - 32) * (5 / 9.0)).toBigDecimal()
                                        .setScale(1, RoundingMode.CEILING).toFloat()
                                    min = ((min - 32) * (5 / 9.0)).toBigDecimal()
                                        .setScale(1, RoundingMode.CEILING).toFloat()
                                    database.addtemp(
                                        date = date,
                                        maxtemp = max.toString(),
                                        mintemp = min.toString()
                                    )

                                    var year = date.split("-")[0].toInt()
                                    var month = date.split("-")[1]
                                    var dte = date.split("-")[2]

                                    Log.d("Year", year.toString())
                                    Log.d("Month", month.toString())
                                    Log.d("dte", dte.toString())

//                              Inserting and fetching previous 10 year dates

                                    for (i in 1..10) {
                                        year = year - 1
                                        var newDate =
                                            year.toString() + "-" + month + "-" + dte
                                        Log.d("NewDate", newDate)
                                        var response = fetchTemperature(newDate)
                                        if (response.body().toString() != "") {
                                            var newmax = response.body()!!.days[0].tempmax.toFloat()
                                            var newmin = response.body()!!.days[0].tempmin.toFloat()
                                            newmax = ((newmax - 32) * (5 / 9.0)).toBigDecimal()
                                                .setScale(1, RoundingMode.CEILING).toFloat()
                                            newmin = ((newmin - 32) * (5 / 9.0)).toBigDecimal()
                                                .setScale(1, RoundingMode.CEILING).toFloat()

                                            database.addtemp(
                                                date = newDate,
                                                maxtemp = newmax.toString(),
                                                mintemp = newmin.toString()
                                            )
                                        }
                                    }

                                }
                            }

                        }
                        fetched = true
                    }
                }
                else{
                    fetched=true
                }



            },
            modifier = modifier,
        ) {
            Text(text = "Fetch Temperature")
        }
        if(date!="") {
            Spacer(modifier = modifier.height(15.dp))
            Text(
                text = "Picked Date: " + date,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
//
        }
        if(fetched && date!="" && connected==true && isFuture==false){
            Log.d("Debug1","fetched = True")
            Spacer(modifier = modifier.height(15.dp))
//
//            Log.d("Response",max.toString())
            printTemp(maxtemp = max.toString(), mintemp = min.toString())


            inserted=true
//            fetched=false
//            datePicked=false
        }
        else if(fetched==true && datePicked==false){
            Spacer(modifier = modifier.height(15.dp))
            Text(text = "Please Select the Date",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)

        }
        else if(fetched==true && isFuture==true){
            var year = date.split("-")[0].toInt()
            var month = date.split("-")[1]
            var dte = date.split("-")[2]
            var currentYear = currentDate.split("-")[0].toInt()
            var newMax =BigDecimal("0")
            var newMin = BigDecimal("0")
            dataAvail=true
            if(dataAvail) {
                for (i in 1..11) {

                    var newDate = year.toString() + "-" + month + "-" + dte
                    var newDate1 = currentYear.toString() + "-" + month + "-" + dte
                    if(futureYear==true){
                        val retreivedData = database.readtemp(newDate1)
                        if (retreivedData.size == 0) {
                            dataAvail = false
                            break
                        }
                        else {
                            newMax = newMax.add(retreivedData[1].toBigDecimal())
                            newMin = newMin.add(retreivedData[2].toBigDecimal())
                            Log.d("Average", newMax.toString())
                            Log.d("Average", newMin.toString())
                        }
                    }
                    else {
                        val retreivedData = database.readtemp(newDate)
                        if (retreivedData.size == 0) {
                            dataAvail = false
                            break
                        } else {
                            newMax = newMax.add(retreivedData[1].toBigDecimal())
                            newMin = newMin.add(retreivedData[2].toBigDecimal())

                            Log.d("Average", newMax.toString())
                            Log.d("Average", newMin.toString())

                        }
                    }
                    year = year - 1
                    currentYear = currentYear-1

                }
                newMax = newMax.divide(BigDecimal.TEN)
                newMin = newMin.divide(BigDecimal.TEN)

                if(dataAvail){
//                    newMax=newMax.toBigDecimal().setScale(1, RoundingMode.CEILING).toFloat()
//                    newMin=newMin.toBigDecimal().setScale(1, RoundingMode.CEILING).toFloat()
                    Log.d("Data Avial","True")
//
                    printTemp(maxtemp = newMax.toString(), mintemp = newMin.toString())
                }

            }
            if(dataAvail==false){
                Text(text = "No Available data to show",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
            }
        }
        else if(fetched==true && connected==false){
            Spacer(modifier = modifier.height(15.dp))
            val retreivedData = database.readtemp(date)
            if(retreivedData.size!=0){
                val readDate = retreivedData[0]
                val readMax = retreivedData[1]
                val readMin = retreivedData[2]
//
                printTemp(maxtemp = readMax.toString(), mintemp = readMin.toString())

            }
            else {
                Text(
                    text = "No Available Data to show",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        Homescreen()
    }
}