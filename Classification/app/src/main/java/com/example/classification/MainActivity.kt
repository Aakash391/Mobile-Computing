package com.example.classification

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classification.ui.theme.ClassificationTheme
import coil.compose.AsyncImage
import com.example.classification.ml.MobilenetV110224Quant
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class MainActivity : ComponentActivity() {
    companion object {
        init {
            System.loadLibrary("classification")
        }
    }
    external fun stringFromJNI(): String
    external fun findMaxIndex(outputFeature0: FloatArray): Int

    val jniString =  stringFromJNI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val labels = application.assets.open("Labels.txt").bufferedReader().readLines()
        setContent {
            ClassificationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(jniString,labels)
                }
            }
        }


    }


}

@Composable
fun Greeting(str:String, labels:List<String>,modifier: Modifier = Modifier){
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var photoPicker = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri -> imageUri = uri})

    var imageProcessor = ImageProcessor.Builder().add(ResizeOp(224,224,ResizeOp.ResizeMethod.BILINEAR)).build()

    var show by remember {
        mutableStateOf(false)
    }
    var predicted by remember {
        mutableStateOf(false)
    }
    var max by remember {
        mutableStateOf(0)
    }
    var loaded by remember {
        mutableStateOf(false)
    }
    Column (modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = str, modifier = modifier)
        Spacer(modifier = modifier.height(10.dp))
        if(loaded){
            AsyncImage(model = imageUri, contentDescription = null,modifier=modifier.size(300.dp))
        }
        Spacer(modifier = modifier.height(10.dp))
        Button(onClick = {
            loaded=true
            photoPicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text(text = "LoadImage")

        }


        Spacer(modifier = modifier.height(10.dp))
        Button(onClick = {
            var tensorImage = TensorImage(DataType.UINT8)
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            tensorImage.load(bitmap)

            tensorImage = imageProcessor.process(tensorImage)

            val model = MobilenetV110224Quant.newInstance(context)

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
            inputFeature0.loadBuffer(tensorImage.buffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray


//            outputFeature0.forEachIndexed({index,cv -> if(outputFeature0[max]<cv){
//                max=index
//                }
//            })

            val maxIndex = (context as MainActivity).findMaxIndex(outputFeature0)
            max = maxIndex
            predicted = true

            model.close()
        }) {
            Spacer(modifier = modifier.height(10.dp))
            Text(text="Predict")
        }
        if(predicted==true) {
            Text(text = labels[max],
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClassificationTheme {
        var dummy:List<String> = listOf()
        Greeting("",dummy)
    }
}