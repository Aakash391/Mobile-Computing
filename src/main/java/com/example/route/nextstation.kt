package com.example.route

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.RoundingMode

@Composable
fun NextStation(stopNumber:Int,inkilo:Boolean,totalDistanceLeft:Float,totDistancecovered:Float,size:Int,totalDistanceLeftinmiles: Float,totDistancecoveredinmiles:Float,modifier:Modifier= Modifier){
    Row (modifier= modifier.padding(20.dp) )
    {
        Column(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xff82b1e9))
                .width(120.dp).height(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Distance Covered",
                fontSize = 14.sp,
                color = Color(0xff474747),
                fontWeight = FontWeight.Bold
            )
            if (inkilo == false) {
                val roundedCeiling =
                    (0.6f * totDistancecovered).toBigDecimal().setScale(1,RoundingMode.HALF_EVEN)
                        .toDouble()
                Text(
                    text = "" + totDistancecoveredinmiles + " mi",
                    fontSize = 24.sp,
                    color = Color(0xff262627),
                    fontWeight = FontWeight.Bold
                )
            } else {
                val roundedCeiling =
                    (totDistancecovered).toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()
                Text(
                    text = "" + roundedCeiling + " km",
                    fontSize = 24.sp,
                    color = Color(0xff262627),
                    fontWeight = FontWeight.Bold
                )
            }

        }
        Spacer(modifier = Modifier.size(20.dp))

        Column(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xff82b1e9))
                .width(120.dp).height(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Distance Left",
                fontSize = 14.sp,
                color = Color(0xff474747),
                fontWeight = FontWeight.Bold
            )
            if (inkilo == false) {
                val roundedCeiling =
                    (0.6f* totalDistanceLeft).toBigDecimal().setScale(1,RoundingMode.HALF_EVEN)
                        .toDouble()
                Text(
                    text = "" + totalDistanceLeftinmiles + " mi",
                    fontSize = 24.sp,
                    color = Color(0xff262627),
                    fontWeight = FontWeight.Bold
                )
            } else {
                val roundedCeiling =
                    (totalDistanceLeft).toBigDecimal().setScale(1, RoundingMode.FLOOR).toDouble()
                Text(
                    text = "" + roundedCeiling + " km",
                    fontSize = 24.sp,
                    color = Color(0xff262627),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
        if(stopNumber<=size) {
            Row(modifier = modifier.padding(10.dp)) {
                Text(
                    text = "Next Station  : ",
                    fontSize = 20.sp,
                    color = Color(0xffafbfab),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "" + totalstops[stopNumber].stopName,
                    fontSize = 20.sp,
                    color = Color(0xff39da12),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
        }



}