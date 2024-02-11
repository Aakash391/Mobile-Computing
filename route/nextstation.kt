package com.example.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.RoundingMode

@Composable
fun NextStation(stopNumber:Int,inkilo:Boolean,totalDistanceLeft:Float,totDistancecovered:Float){
    Column (modifier= Modifier.padding(50.dp) )
    {
        Row(){
            Text(
                text="Total Distance Covered : ",
                fontSize = 12.sp,
                color= Color(0xffafbfab),
                fontWeight = FontWeight.Bold
            )
            if(inkilo==false){
                val roundedCeiling = (0.6*totDistancecovered).toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()
                Text(
                    text=""+roundedCeiling+" mi",
                    fontSize = 12.sp,
                    color= Color(0xff39da12),
                    fontWeight = FontWeight.Bold
                )
            }
            else {
                val roundedCeiling = (totDistancecovered).toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()
                Text(
                    text = "" + roundedCeiling + " km",
                    fontSize = 12.sp,
                    color = Color(0xff39da12),
                    fontWeight = FontWeight.Bold
                )
            }

        }
        Row(){
            Text(
                text="Total Distance Left : ",
                fontSize=12.sp,
                color= Color(0xffafbfab),
                fontWeight = FontWeight.Bold
            )
            if(inkilo==false){
                val roundedCeiling = (0.6*totalDistanceLeft).toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()
                Text(
                    text=""+roundedCeiling+" mi",
                    fontSize = 12.sp,
                    color= Color(0xff39da12),
                    fontWeight = FontWeight.Bold
                )
            }
            else {
                val roundedCeiling = (totalDistanceLeft).toBigDecimal().setScale(1, RoundingMode.FLOOR).toDouble()
                Text(
                    text = "" + roundedCeiling + " km",
                    fontSize = 12.sp,
                    color = Color(0xff39da12),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row() {
            Text(
                text = "Next Station  : ",
                fontSize = 12.sp,
                color = Color(0xffafbfab),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = ""+ totalstops[stopNumber].stopName,
                fontSize = 12.sp,
                color = Color(0xff39da12),
                fontWeight = FontWeight.Bold
            )
        }

    }
}