package com.example.applicationwithstartscreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.Text as Text1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatingCard(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val birthday = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf("") }
    val isButtonEnabledName = name.value.isNotEmpty()
    val isButtonEnabledLastName = lastname.value.isNotEmpty()
    val isButtonEnabledBirthday = birthday.value.isNotEmpty()
    val isButtonEnabledGender = gender.value.isNotEmpty()
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val genderOptions = arrayOf("Мужской", "Женский")
    val focusRequester = remember { FocusRequester() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 250.dp, top = 80.dp, bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text1(
            text = "Пропустить",
            color = colorResource(R.color.new_color),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .clickable(
                    onClick = {
                        navController.navigate("analized")
                    }
                )
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 23.dp)
            .height(200.dp),
        verticalAlignment = Alignment.CenterVertically,
    )
    {
        Text1(
            text = "Создание карты\nпациента",
            fontSize = 25.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text1(
            text = "Без карты пациента вы не сможете заказать анализы.",
            fontSize = 13.sp,
            modifier = Modifier
                .padding(top = 140.dp, start = 23.dp),
            color = Color.Gray
        )
        Text1(
            text = "В картах пациентов будут храниться результаты\nанализов вас и ваших близких",
            fontSize = 13.sp,
            modifier = Modifier
                .padding(top = 5.dp, start = 23.dp),
            color = Color.Gray
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 250.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            name.value,
            { name.value = it },
            label = { Text1(text = "Имя") },
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
                .height(60.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            surname.value,
            { surname.value = it },
            label = { Text1(text = "Отчество") },
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
                .height(60.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            lastname.value,
            { lastname.value = it },
            label = { Text1(text = "Фамилия") },
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
                .height(60.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            birthday.value,
            { birthday.value = it },
            label = { Text1(text = "Дата рождения") },
            textStyle = TextStyle(fontSize = 15.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                focusedBorderColor = colorResource(R.color.new_color),
                unfocusedBorderColor = Color.Gray,
                unfocusedTextColor = Color.Gray,
                unfocusedContainerColor = Color(0xffeeeeee)
            ),
            trailingIcon = {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = "Select date",
                    modifier = Modifier.clickable { showModal = true }
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(380.dp)
                .height(60.dp)
                .focusRequester(focusRequester),

            )
        if (showModal) {
            Popup(
                alignment = Alignment.Center,
                onDismissRequest = { showModal = false }
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White, RoundedCornerShape(15.dp))
                        .padding(16.dp)
                ) {
                    val datePickerState = rememberDatePickerState()
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DatePicker(state = datePickerState)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                val selectedDateInMillis = datePickerState.selectedDateMillis
                                if (selectedDateInMillis != null) {
                                    birthday.value = convertMillisToDate(selectedDateInMillis)
                                }
                                showModal = false
                            }
                        ) {
                            Text1(text = "Выбрать дату")
                        }
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            OutlinedTextField(
                value = gender.value,
                onValueChange = {},
                readOnly = true,
                label = { Text1(text = "Пол") },
                modifier = Modifier
                    .width(380.dp)
                    .height(60.dp)
                    .clickable { expanded = true }, // Открытие меню по клику
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select gender",
                        modifier = Modifier.clickable { expanded = !expanded } // Переключение меню
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    focusedBorderColor = colorResource(R.color.new_color),
                    unfocusedBorderColor = Color.Gray,
                    unfocusedTextColor = Color.Gray,
                    unfocusedContainerColor = Color(0xffeeeeee),
                ),
                shape = RoundedCornerShape(20.dp),
            )
        }

        // Выпадающий список
        if (expanded) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(380.dp)
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                ) {
                    genderOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text1(text = option) },
                            onClick = {
                                gender.value = option // Выбор значения
                                expanded = false // Закрытие меню
                            }
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom

        )
        {
            Button(
                onClick = {
                    if (isButtonEnabledName && isButtonEnabledLastName && isButtonEnabledBirthday && isButtonEnabledGender) {
                        navController.navigate("analized")
                    }
                },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .width(380.dp)
                    .height(70.dp),
                enabled = isButtonEnabledName && isButtonEnabledLastName && isButtonEnabledBirthday && isButtonEnabledGender,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabledName && isButtonEnabledLastName && isButtonEnabledBirthday && isButtonEnabledGender) colorResource(
                        R.color.new_color
                    ) else colorResource(R.color.new_color_buttom),
                    contentColor = Color.White,
                )
            ) {
                Text1(
                    "Далее",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}
