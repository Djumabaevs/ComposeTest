package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetest.ui.theme.ComposeTestTheme
import java.time.format.TextStyle
import kotlin.random.Random
import androidx.compose.runtime.mutableStateOf as mutableStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fontFamily = FontFamily(
            Font(R.font.lexend_thin, FontWeight.Thin),
            Font(R.font.lexend_light, FontWeight.Light),
            Font(R.font.lexend_regular, FontWeight.Normal),
            Font(R.font.lexend_medium, FontWeight.Medium),
            Font(R.font.lexend_semibold, FontWeight.SemiBold),
            Font(R.font.lexend_bold, FontWeight.Bold),
            Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
        )

        setContent {


            val painter = painterResource(id = R.drawable.dino)
            val description = "Dinos in the park"
            val title = "Dinos playing in the park"

            val color = remember {
                mutableStateOf (Color.Yellow)
            }

            Column(Modifier.fillMaxSize()) {

                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(color.value)
//                    .background(Color(0xFF101010))
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Green,
                                    fontSize = 50.sp
                                )
                            ) {
                                append("J")
                            }
                            append("etPack ")
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Green,
                                    fontSize = 50.sp
                                )
                            ) {
                                append("C")
                            }
                            append("ompose")
                        },
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline

                    )



                }

                Box(modifier = Modifier
                    .weight(1f).fillMaxSize())
                {
                    ImageCard(
                        painter = painter,
                        contentDescription = description,
                        title = title)
                }

                ColorBox(Modifier
                    .fillMaxSize()
                    .weight(1f)) {
                        color.value = it
                }

            }

            





          /*      Column(modifier = Modifier
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
                        .offset(50.dp, 20.dp)
                        .clickable {

                        })
                    Spacer(modifier = Modifier.height(200.dp))
                    Text("Developer", modifier = Modifier
                        .border(15.dp, Color.Black)
                        .padding(10.dp)
                        .border(20.dp, Color.Blue)
                        .padding(15.dp))
                }*/
        }
    }


    @Composable
    fun ColorBox(modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit) {

        Box(modifier = modifier
            .background(Color.Red)
            .clickable {
                updateColor (
                     Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                         1F
                                  )
                        )
            }
            .offset(100.dp, 100.dp))
    }


    @Composable
    fun ImageCard(
        painter: Painter,
        contentDescription: String,
        title: String,
         modifier: Modifier = Modifier) {

        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Box(modifier = Modifier.height(200.dp))
            {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop)

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Green
                            ),
                            startY = 300f
                        )
                    ))

                Text(text = "Dinos in the park!",
                     modifier = Modifier
                         .align(Alignment.BottomStart)
                         .padding(12.dp),
                     fontSize = 16.sp,
                     color = Color.White
                    )
                
/*                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                     contentAlignment = Alignment.BottomStart)
                {
                    Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
                }*/
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
