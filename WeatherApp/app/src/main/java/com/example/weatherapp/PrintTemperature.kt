package com.example.weatherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun printTemp(maxtemp:String, mintemp:String,modifier:Modifier= Modifier){

    Row (modifier= modifier.padding(20.dp) )
    {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
//                .background(Color(0xff82b1e9))
                .width(140.dp)
                .height(90.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Maximum",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
//                color = Color(0xFFfa6025)
                )
            Text(text = "Temperature",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
                )
            Text(text = maxtemp + " °C",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp)
        }

        Spacer(modifier = modifier.width(14.dp))

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
//                .background(Color(0xff82b1e9))
                .width(140.dp)
                .height(90.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Minimum",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
                )
            Text(text = "Temperature",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
                )
            Text(text = mintemp + " °C",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp)
        }


    }
}