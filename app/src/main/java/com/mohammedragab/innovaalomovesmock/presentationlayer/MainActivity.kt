package com.mohammedragab.innovaalomovesmock.presentationlayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.DataState
import com.mohammedragab.innovaalomovesmock.presentationlayer.trainingseries.trainingseriesviewmodel.TrainingSeriesViewModel
import com.mohammedragab.innovaalomovesmock.ui.theme.InnovaAloMovesMockTheme
import org.koin.java.KoinJavaComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val trainingSeriesViewModel: TrainingSeriesViewModel = KoinJavaComponent.get(TrainingSeriesViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContent {
            InnovaAloMovesMockTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(key1 = true) { trainingSeriesViewModel.getTrainingSeries() }

                    val dataStateN by trainingSeriesViewModel.dataState.collectAsState()

                    when (dataStateN) {
                        is DataState.Loading -> {
                            CircularProgressIndicator()

                        }

                        is DataState.Error -> {

                            // here will show error

                        }

                        is DataState.Complete -> {

                            // here will show data
                            Column(
                                modifier = Modifier
                                    .background(Color.Green)
                                    .fillMaxSize()
                                    .scrollable(
                                        state = rememberScrollState(),
                                        orientation = Orientation.Vertical
                                    )
                                    .verticalScroll(state = rememberScrollState())
                            ) {



                            }
                        }


                        else -> {}
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InnovaAloMovesMockTheme {
        Greeting("Android")
    }
}