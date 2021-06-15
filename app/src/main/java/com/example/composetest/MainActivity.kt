package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest.ui.theme.ComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Column(modifier = Modifier
                    .background(Color.Cyan)
                    .fillMaxHeight(0.5f)
                    .width(500.dp)
                    .padding(20.dp)
                    .border(5.dp, Color.DarkGray)
                    .padding(15.dp)
                    .border(10.dp, Color.Red, shape = RectangleShape),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                   ) {
                    Text("Bakyt is", modifier = Modifier
                        .offset(50.dp, 20.dp))
                    Spacer(modifier = Modifier.height(200.dp))
                    Text("Developer")
                }
        }
    }
}
/*

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
*/

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestTheme {
        Greeting("Android")
    }*/
