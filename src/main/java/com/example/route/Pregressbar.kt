package com.example.route

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun showprogress(currentProgress:Float,list:Boolean,lazy:Boolean,modifier:Modifier= Modifier){
    Row(
        modifier=modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text="Badarpur",
            color = Color(0xffec0000),
            fontSize = 14.sp,
            modifier= modifier
                .background(Color(0xff1f1e25)).padding(5.dp),
            fontWeight = FontWeight.Bold
        )
        LinearProgressIndicator(
            progress = currentProgress,
            color = Color(0xff39da12),
            trackColor = Color(0xffece7e6),
            modifier=modifier.width(170.dp).height(5.dp)
        )
        if(list==true) {
            Text(
                text = "Moolchand",
                color = Color.Green,
                fontSize = 14.sp,
                modifier = modifier.padding(5.dp)

                    .background(Color(0xff1f1e25)),
                fontWeight = FontWeight.Bold
            )
        }
        else{
            Text(
                text = "Kashmere Gate",
                color = Color.Green,
                fontSize = 14.sp,
                modifier = modifier

                    .background(Color(0xff1f1e25)).padding(5.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}