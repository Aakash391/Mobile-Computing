
//import android.graphics.Point
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import com.example.sensor.ui.theme.Database
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine

@Composable
fun lineChartforRoll(database: Database){
    val steps = 5
    var time=0;
    var points = mutableListOf<Point>()
    val dataPoints = database.readRoll().map { it.toFloat() }
    Log.d("Data",dataPoints.toString())
    for(point in dataPoints){
        points.add(Point((time).toFloat(),point))
        time+=1
    }

    val maxY = points.maxByOrNull { it.y }?.y ?: 0f
    val minY = points.minByOrNull { it.y }?.y ?: 0f
    val yRange = maxY - minY
    val yStep = yRange / steps

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.LightGray)
        .steps(points.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.LightGray)
        .labelData { i ->
            (minY + i * yStep).toInt().toString()
        }
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = points,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(Color.Red),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )

    LineChart(
        modifier = Modifier.size(400.dp,200.dp),
        lineChartData = lineChartData
    )

}

@Composable
fun lineChartforPitch(database: Database){
    val steps = 5
    var time=0;
    var points = mutableListOf<Point>()
    val dataPoints = database.readPitch().map { it.toFloat() }
    Log.d("Data",dataPoints.toString())
    for(point in dataPoints){
        points.add(Point((time).toFloat(),point))
        time+=1
    }

    val maxY = points.maxByOrNull { it.y }?.y ?: 0f
    val minY = points.minByOrNull { it.y }?.y ?: 0f
    val yRange = maxY - minY
    val yStep = yRange / steps

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.LightGray)
        .steps(points.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.LightGray)
        .labelData { i ->
            (minY + i * yStep).toInt().toString()
        }
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = points,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(Color.Green),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )

    LineChart(
        modifier = Modifier.size(400.dp,200.dp),
        lineChartData = lineChartData
    )

}

@Composable
fun lineChartforYaw(database: Database){
    val steps = 5
    var time=0;
    var points = mutableListOf<Point>()
    val dataPoints = database.readYaw().map { it.toFloat() }
    Log.d("Data",dataPoints.toString())
    for(point in dataPoints){
        points.add(Point((time).toFloat(),point))
        time+=1
    }

    val maxY = points.maxByOrNull { it.y }?.y ?: 0f
    val minY = points.minByOrNull { it.y }?.y ?: 0f
    val yRange = maxY - minY
    val yStep = yRange / steps

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.LightGray)
        .steps(points.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.LightGray)
        .labelData { i ->
            (minY + i * yStep).toInt().toString()
        }
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = points,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(Color.Blue),
                    SelectionHighlightPopUp(),

                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )

    LineChart(
        modifier = Modifier.size(400.dp,200.dp),
        lineChartData = lineChartData
    )

}