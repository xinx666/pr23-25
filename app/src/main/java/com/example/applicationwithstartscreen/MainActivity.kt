package com.example.applicationwithstartscreen

import CreatingPassword
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applicationwithstartscreen.ui.theme.ApplicationWithStartScreenTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationWithStartScreenTheme {
                val navController = rememberNavController()
                var isSplashScreenVisible by remember { mutableStateOf(true) }

                if (isSplashScreenVisible) {
                    SplashScreen(
                        navController = navController, // Pass navController here
                        onTimeout = { isSplashScreenVisible = false }
                    )
                }
                NavHost(navController = navController, startDestination = "SplashScreen") {
                    composable("SplashScreen") {
                        // SplashScreen content will be handled by the composable
                    }
                    composable("OnBoarding") {
                        OnBoarding(navController)
                    }
                    composable("autorization_screen") {
                        AutorizationAndRegistration(navController)
                    }
                    composable("code_in_email") {
                        CodeInEmail(navController)
                    }
                    composable("creating_password") {
                        CreatingPassword(navController)
                    }
                    composable("creating_card") {
                        CreatingCard(navController)
                    }
                    composable("analized"){
                        Analyzes(navController)
                    }
                }
            }
        }
    }

    @Composable
    fun SplashScreen(navController: NavController, onTimeout: () -> Unit) {
        LaunchedEffect(Unit) {
            delay(3000)
            onTimeout()
            navController.navigate("OnBoarding")
        }
        val colorList1 = listOf(
            colorResource(R.color.color1),
            colorResource(R.color.color2),
            colorResource(R.color.color1)
        )
        val colorList2 = listOf(
            colorResource(R.color.color4),
            colorResource(R.color.color5),
            colorResource(R.color.color4)
        )
        val colorList3 = listOf(
            colorResource(R.color.color7),
            colorResource(R.color.color8),
            colorResource(R.color.color9),
            colorResource(R.color.color8),
            colorResource(R.color.color7)
        )
        var containerWidthPx by remember { mutableStateOf(0f) }
        var containerHeightPx by remember { mutableStateOf(0f) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { size: IntSize ->
                    containerWidthPx = size.width.toFloat()
                    containerHeightPx = size.height.toFloat()
                }
                .background(
                    brush = Brush.linearGradient(
                        colors = colorList1,
                        start = Offset(containerWidthPx, 0f),
                        end = Offset(0f, containerHeightPx)
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = colorList2,
                            start = Offset(containerWidthPx, 0f),
                            end = Offset(0f, containerHeightPx)
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = colorList3,
                                start = Offset(
                                    containerWidthPx / 2,
                                    0f
                                ), // Начало в центре по ширине, сверху
                                end = Offset(containerWidthPx / 2, containerHeightPx)
                            )
                        )
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            text = "Смартлаб",
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                        Image(
                            modifier = Modifier
                                .size(50.dp),
                            bitmap = ImageBitmap.imageResource(R.drawable.logo),
                            contentDescription = "Logo"
                        )
                    }
                }
            }
        }
    }
}
