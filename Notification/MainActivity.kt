package com.example.myapplication

import android.Manifest
import android.Manifest.permission
import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.myapplication.ui.theme.MyApplicationTheme
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                val context= LocalContext.current
                var hasNotificationPermission by remember {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        mutableStateOf(
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        )==PackageManager.PERMISSION_GRANTED
                        )
                    } else {
                       mutableStateOf(true)
                    }
                }

                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = {isGranted ->hasNotificationPermission=isGranted
                        }
                    )
                    Button(onClick = { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }

                    }) {
                        Text(text="Request Permission")
                    }

                    Button(onClick = {
                        if(hasNotificationPermission){
                            showNotification()
                        }
                        }) {
                        Text(text="Show Permission")
                    }
                }

            }
        }
    }
    private fun showNotification(){
        val notification = NotificationCompat.Builder(applicationContext,"channel id")
            .setContentText("This is content text")
            .setContentTitle("Hello World")
            .build()

        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(1,notification)
    }
}



