package com.example.applicationwithstartscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AuthorizationAndSignUp(navController: NavController) {
    val userEmail = remember { mutableStateOf("") }
    val isButtonActive = userEmail.value.isNotEmpty()

    Column(modifier = Modifier.fillMaxSize()) {
        // Заголовок с изображением
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.padding(start = 20.dp))
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.emojies),
                contentDescription = "welcome_icon",
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "Добро пожаловать!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        // Текст приветствия
        Column(modifier = Modifier.padding(top = 100.dp, start = 23.dp)) {
            Text(
                text = "Войдите, чтобы продолжить использование\nприложения",
                fontSize = 16.sp,
            )
        }

        // Поле ввода email
        Column(modifier = Modifier.padding(top = 210.dp, start = 20.dp)) {
            Text(
                text = "Введите E-mail:",
                fontSize = 15.sp,
                color = Color.Gray
            )
        }

        // Поле для ввода email
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 240.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            OutlinedTextField(
                value = userEmail.value,
                onValueChange = { userEmail.value = it },
                textStyle = TextStyle(fontSize = 15.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    focusedBorderColor = colorResource(R.color.new_color),
                    unfocusedBorderColor = Color.Gray,
                    unfocusedTextColor = Color.Gray,
                    unfocusedContainerColor = Color(0xffeeeeee)
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(380.dp)
                    .height(50.dp)
            )
        }

        // Кнопка "Далее"
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (isButtonActive) {
                        navController.navigate("email_verification")
                    }
                },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .width(380.dp)
                    .height(70.dp),
                enabled = isButtonActive,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.new_color),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(R.color.new_color_buttom),
                )
            ) {
                Text(
                    text = "Продолжить",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }

        // Вход с помощью Яндекс
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Или войдите с использованием",
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    // Логика для Яндекс входа
                },
                modifier = Modifier
                    .width(380.dp)
                    .height(70.dp)
                    .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                )
            ) {
                Text("Войти через Яндекс")
            }
        }
    }
}
