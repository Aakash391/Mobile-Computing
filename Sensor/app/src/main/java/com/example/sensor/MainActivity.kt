package com.example.sensor

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sensor.ui.theme.Database
import com.example.sensor.ui.theme.SensorTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import lineChartforPitch
import lineChartforRoll
import lineChartforYaw
import kotlin.math.atan2


class MainActivity : ComponentActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private var roll by mutableStateOf(0.0)
    private var pitch by mutableStateOf(0.0)
    private var azimuth by mutableStateOf(0.0)
    private lateinit var database: Database


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        database = Database(this)



        setContent {
            SensorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OrientationDisplay(roll, pitch, azimuth, database,Modifier)
                }
            }
        }



    }

    override fun onResume() {
        super.onResume()

        sensorManager.registerListener(
            this,
            accelerometer,
            1000000

        )

        startDataCollection()
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)

        stopDataCollection()
    }
    fun stopDataCollection(){

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun startDataCollection() {
        GlobalScope.launch {
            while (isActive) {
                Log.d("Roll: ",roll.toString())
                Log.d("Pitch: ",pitch.toString())
                Log.d("Azimuth: ",azimuth.toString())

                storeOrientationData(roll.toString(), pitch.toString(), azimuth.toString(),System.currentTimeMillis().toString())
                delay(1000)
            }
        }
    }

    private fun storeOrientationData(roll: String, pitch: String, yaw: String,time: String) {
        database.addData(roll, pitch, yaw, time)
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {


        Log.d("interval,","interval")
        if (event != null) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val alpha = 0.8f
                val gravity = FloatArray(3)
                val linear_acceleration = FloatArray(3)

                linear_acceleration[0] = event.values[0]
                linear_acceleration[1] = event.values[1]
                linear_acceleration[2] = event.values[2]

                roll = linear_acceleration[0].toDouble()
                pitch = linear_acceleration[1].toDouble()
                azimuth = linear_acceleration[2].toDouble()
            }
        }
//        updateUI(roll, pitch, azimuth)


    }



    private fun updateUI(roll: Double, pitch: Double, azimuth: Double) {

            setContent {
                SensorTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        OrientationDisplay(roll, pitch, azimuth,database,Modifier)
                    }
                }
            }

    }
}

@Composable
fun OrientationDisplay(roll: Double, pitch: Double, azimuth: Double, database:Database, modifier:Modifier) {

    val context = LocalContext.current
    Column (
        modifier =modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier.height(30.dp))
        Text(text = "Roll: $roll", modifier = Modifier.padding(8.dp))
        Text(text = "Pitch: $pitch", modifier = Modifier.padding(8.dp))
        Text(text = "Azimuth: $azimuth", modifier = Modifier.padding(8.dp))

        Spacer(Modifier.height(200.dp))

        Button(onClick = {
            val intent = Intent(context, GraphActivity::class.java).apply{

            }
            context.startActivity(intent)

        }) {
            Text(text = "Show Graph")
        }


    }
}

class GraphActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SensorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    makeGraph(Modifier)
                }
            }
        }
    }
}
@Composable
fun makeGraph(modifier: Modifier){
    val context = LocalContext.current
    val database = Database(context)

    Column (modifier=modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        lineChartforRoll(database = database)
        Text("Roll vs Time")
        Spacer(modifier = modifier.height(6.dp) )
        lineChartforPitch(database = database)
        Text("Pitch vs Time")
        Spacer(modifier = modifier.height(6.dp) )
        lineChartforYaw(database = database)
        Text("Yaw vs Time")
        Spacer(modifier = modifier.height(6.dp) )

        Button(onClick = {
            val intent = Intent(context, MainActivity::class.java).apply{

            }
            context.startActivity(intent)
        }) {
            Text(text = "Back")
        }
    }



}
@Preview(showBackground = true)
@Composable
fun OrientationPreview() {
    SensorTheme {
        val context = LocalContext.current
        val database = Database(context)
        OrientationDisplay(45.0, 30.0, 60.0,database,Modifier)
    }
}
