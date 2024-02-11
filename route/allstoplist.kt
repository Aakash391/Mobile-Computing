package com.example.route

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import java.math.RoundingMode
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp

@Composable
fun AllStopList(stopNumber:Int,inkilo:Boolean,modifier:Modifier= Modifier){
    LazyColumn(modifier=modifier.size(164.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start) {
        items(totalstops.size) {
                index ->
            Row() {
                Column(
                    modifier = modifier.fillMaxWidth(0.74f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {


                    if (totalstops[index].id == stopNumber + 1) {
                        Text(
                            text = totalstops[index].stopName + " : ",
                            fontSize = 12.sp,
                            color = Color(0xff39da12),
                            fontWeight = FontWeight.Bold
                        )

                    } else {
                        if (totalstops[index].id < stopNumber + 1) {
                            Text(
                                text = totalstops[index].stopName + " : ",
                                fontSize = 12.sp,
                                color = Color(0xffd52209),
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Text(
                                text = totalstops[index].stopName + " : ",
                                fontSize = 12.sp,
                                color = Color(0xffa7a2a1),

                                )
                        }
                    }


                }
                Column(modifier=modifier,
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center) {

                    if (totalstops[index].id == stopNumber + 1) {
                        if (inkilo == false) {
                            val roundedCeiling =
                                (0.6f * totalstops[index].distanceFromPreviousStop).toBigDecimal()
                                    .setScale(1, RoundingMode.CEILING).toDouble()
                            Text(
                                text = "" + roundedCeiling + " mi",
                                fontSize = 12.sp,
                                color = Color(0xff39da12),
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Text(
                                text = "" + totalstops[index].distanceFromPreviousStop + " km",
                                fontSize = 12.sp,
                                color = Color(0xff39da12),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        if (totalstops[index].id < stopNumber + 1) {
                            val roundedCeiling =
                                (0.6f * totalstops[index].distanceFromPreviousStop).toBigDecimal()
                                    .setScale(1, RoundingMode.CEILING).toDouble()
                            if (inkilo == false) {
                                Text(
                                    text = "" + roundedCeiling + " mi",
                                    fontSize = 12.sp,
                                    color = Color(0xffd52209),
                                    fontWeight = FontWeight.Bold
                                )
                            } else {
                                Text(
                                    text = "" + totalstops[index].distanceFromPreviousStop + " km",
                                    fontSize = 12.sp,
                                    color = Color(0xffd52209),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            if (inkilo == false) {
                                val roundedCeiling =
                                    (0.6f * totalstops[index].distanceFromPreviousStop).toBigDecimal()
                                        .setScale(1, RoundingMode.CEILING).toDouble()
                                Text(
                                    text = "" + roundedCeiling + " mi",
                                    fontSize = 12.sp,
                                    color = Color(0xffa7a2a1)
                                )
                            } else {
                                Text(
                                    text = "" + totalstops[index].distanceFromPreviousStop + " km",
                                    fontSize = 12.sp,
                                    color = Color(0xffa7a2a1)
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}