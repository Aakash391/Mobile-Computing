package com.example.route

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.route.ui.theme.RouteTheme
import java.math.RoundingMode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RouteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Route()
                }
            }
        }
    }
}

@Composable
fun ReachedNextStop(allstops: List<Stop>,modifier: Modifier= Modifier){
    var stopNumber by remember { mutableStateOf( 0) }

    var totalDistanceLeft by remember {
        mutableStateOf(11.3f)
    }
    var totalDistanceLeftinmiles by remember {
        mutableStateOf(6.7f)
    }

    var totDistancecovered by remember {
        mutableStateOf(0.0f)
    }

    var totDistancecoveredinmiles by remember {
        mutableStateOf(0.0f)
    }

    var currentProgress by remember { mutableStateOf(0f) }

    var inkilo by remember {
        mutableStateOf(true)
    }
    var list by remember {
        mutableStateOf(false)
    }
    var lazy by remember {
        mutableStateOf(false)
    }
    var listsize by remember {
        mutableStateOf(9)
    }
    var startJourney by remember {
        mutableStateOf(false)
    }

    if(startJourney==false){

        Column(
            modifier = modifier.background(Color(0xff1f1e25)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Button(onClick = { list=true
                             startJourney=true
                             totalDistanceLeft=11.3f
                             totalDistanceLeftinmiles=6.7f},
                colors = ButtonDefaults.outlinedButtonColors(Color(0xffe87b07)),
                modifier = Modifier.padding(10.dp))
            {
                Text(text = "Normal List",
                    fontSize = 15.sp,
                    color = Color(0xffd8fd77)
                )
            }
            Button(onClick = { lazy=true
                                startJourney=true
                             listsize=21
                             totalDistanceLeft=23.0f
                             totalDistanceLeftinmiles=13.8f},
                colors = ButtonDefaults.outlinedButtonColors(Color(0xffe87b07)),
                modifier = Modifier.padding(10.dp))
            {
                Text(text = "Lazy List",
                    fontSize = 15.sp,
                    color = Color(0xffd8fd77)
                )
            }
        }
    }
    if(startJourney==true) {

        Column(
            modifier = modifier.background(Color(0xff1f1e25)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            showprogress(currentProgress = currentProgress,list,lazy)

            @Composable
            fun Inkilometre() {
                NextStation(stopNumber, inkilo, totalDistanceLeft, totDistancecovered,listsize,totalDistanceLeftinmiles,totDistancecoveredinmiles)
                if(list==true){
                    StopList(stopNumber = stopNumber, inkilo = inkilo)
                }
                else {
                    AllStopList(stopNumber, inkilo)
                }
            }

//            @Composable
//            fun Inmiles() {
//                NextStation(stopNumber, inkilo, totalDistanceLeft, totDistancecovered)
//                if(list==true){
//                    StopList(stopNumber = stopNumber, inkilo = inkilo)
//                }
//                else {
//                    AllStopList(stopNumber, inkilo)
//                }
//            }

            if (stopNumber > listsize) {
                currentProgress += 1
                if(list==true){
                    NextStation(
                        stopNumber = stopNumber,
                        inkilo = inkilo,
                        totalDistanceLeft = totalDistanceLeft,
                        totDistancecovered = totDistancecovered,
                        size=listsize,
                        totalDistanceLeftinmiles=totalDistanceLeftinmiles,
                        totDistancecoveredinmiles=totDistancecoveredinmiles
                    )
                    Text(
                        text = "Reached " + allstops[listsize].stopName,
                        fontSize = 16.sp,
                        modifier = modifier.padding(50.dp),
                        color = Color.Green,
                        fontWeight = FontWeight.Bold
                    )

                }
                else {
                    NextStation(
                        stopNumber = stopNumber,
                        inkilo = inkilo,
                        totalDistanceLeft = totalDistanceLeft,
                        totDistancecovered = totDistancecovered,
                        size=listsize,
                        totalDistanceLeftinmiles=totalDistanceLeftinmiles,
                        totDistancecoveredinmiles=totDistancecoveredinmiles
                    )
                    Text(
                        text = "Reached " + totalstops[listsize].stopName,
                        fontSize = 16.sp,
                        modifier = modifier.padding(50.dp),
                        color = Color.Green,
                        fontWeight = FontWeight.Bold
                    )
                }


            } else if (stopNumber <= listsize) {

                Inkilometre()
            }


            Column(
                modifier = modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom

            ) {
                if (stopNumber <= listsize) {
                    Button(
                        onClick = {
                            if (list == true) {
                                totDistancecovered += allstops[stopNumber].distanceFromPreviousStop
                                totalDistanceLeft = 11.3f - totDistancecovered
                                currentProgress += 0.1f
                                totDistancecoveredinmiles=(totDistancecoveredinmiles+0.6*allstops[stopNumber].distanceFromPreviousStop).toBigDecimal().setScale(1,RoundingMode.HALF_EVEN).toFloat()
                                totalDistanceLeftinmiles=(6.7f-totDistancecoveredinmiles).toBigDecimal().setScale(1,RoundingMode.HALF_EVEN).toFloat()
                            } else {
                                totDistancecovered += totalstops[stopNumber].distanceFromPreviousStop
                                totalDistanceLeft = 23.0f - totDistancecovered
                                currentProgress += 0.045f
                                totDistancecoveredinmiles=(totDistancecoveredinmiles+0.6* totalstops[stopNumber].distanceFromPreviousStop).toBigDecimal().setScale(1,RoundingMode.HALF_EVEN).toFloat()
                                totalDistanceLeftinmiles=(13.8f-totDistancecoveredinmiles).toBigDecimal().setScale(1,RoundingMode.HALF_EVEN).toFloat()
                            }

                            stopNumber += 1


                        },
                        colors = ButtonDefaults.outlinedButtonColors(Color(0xffe87b07)),
                        modifier = Modifier.padding(10.dp)

                    ) {
                        Text(
                            text = stringResource(id = R.string.Reached),
                            fontSize = 15.sp,
                            color = Color(0xffd8fd77)

                        )
                    }
                }
                    Button(
                        onClick = {
                            if (inkilo == true)
                                inkilo = false
                            else
                                inkilo = true
                        },
                        colors = ButtonDefaults.outlinedButtonColors(Color(0xffe87b07)),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.Switch),
                            fontSize = 15.sp,
                            color = Color(0xffd8fd77)
                        )
                    }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Route() {
    RouteTheme {

        ReachedNextStop(totalstops)
    }
}