package com.example.applicationwithstartscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun CodeInEmail(navController: NavController) {
    var timeLeft by remember { mutableStateOf(60) }
    var isPaused by remember { mutableStateOf(false) }
    var countText by remember { mutableStateOf("Отправить код повторно можно через ") }
    var resendEnabled by remember { mutableStateOf(false) } // Управляет состоянием повторной отправки

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate("autorization_screen")
            },
            contentColor = Color.Gray,
            containerColor = colorResource(R.color.gray_new)
        ) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back",
                modifier = Modifier.size(40.dp)
            )
        }
    }

    Row(
        modifier = Modifier.padding(top = 200.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Введите код из E-mail",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(top = 40.dp))

            CodeTextField(length = 4) { code ->
                if (code == "1234") { // Замените "1234" на проверку вашего реального кода
                    navController.navigate("creating_password") // Переход на другой экран
                }
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                if (!isPaused) {
                    Text(
                        text = "$countText $timeLeft",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                    )
                } else if (resendEnabled) {
                    Text(
                        text = "Отправить код повторно",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.clickable {
                            timeLeft = 60
                            isPaused = false
                            resendEnabled = false
                            countText = "Отправить код повторно можно через "
                        }
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = timeLeft, key2 = isPaused) {
        while (timeLeft > 0 && !isPaused) {
            delay(1000L)
            timeLeft--
        }
        if (timeLeft == 0) {
            isPaused = true
            resendEnabled = true
        }
    }
}

@Composable
fun CodeTextField(
    length: Int = 4,
    onComplete: (String) -> Unit
) {
    var code by remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(length) { index ->
            val char = code.getOrNull(index)?.toString() ?: ""
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color = colorResource(R.color.gray_new), shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = char,
                    style = TextStyle(fontSize = 24.sp, color = Color.Black)
                )
            }
        }
    }

    BasicTextField(
        value = code,
        onValueChange = {
            if (it.length <= length) {
                code = it
                if (code.length == length) onComplete(code)
            }
        },
        textStyle = TextStyle(fontSize = 0.sp, color = Color.Transparent),
        modifier = Modifier.fillMaxWidth(),
    )
}
