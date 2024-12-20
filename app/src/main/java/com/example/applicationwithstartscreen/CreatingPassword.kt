import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.applicationwithstartscreen.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@Composable
fun CreatingPassword(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 230.dp, top = 50.dp, bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Пропустить",
            color = colorResource(R.color.new_color),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .clickable(
                    onClick = {
                        navController.navigate("autorization_screen")
                    }
                )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Центрирование по вертикали
    ) {
        Text(
            text = "Создайте пароль",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp)) // Изменено для лучшего управления отступами
        Text(
            text = "Для защиты ваших персональных данных",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp)) // Более заметный отступ перед вводом PIN
        PinCodeScreen(navController)
    }

}

@Composable
fun PinCodeScreen(navController: NavController) {
    var pinCode by remember { mutableStateOf("") }
    val maxPinLength = 4
    var buttonColor by remember { mutableStateOf(Color(0xFFF5F5F9)) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Центрирование элементов
    ) {
        // Индикаторы ввода
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            repeat(maxPinLength) { index ->
                val isFilled = index < pinCode.length
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .padding(4.dp)
                        .background(
                            color = if (isFilled) Color(0xFF1A6FEE) else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val rows = listOf(
                listOf("1", "2", "3"),
                listOf("4", "5", "6"),
                listOf("7", "8", "9"),
                listOf("", "0", "←")
            )

            rows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { value ->
                        if (value.isNotEmpty()) {
                            val interactionSource = remember { MutableInteractionSource() }
                            val isPressed by interactionSource.collectIsPressedAsState()

                            Button(
                                onClick = {
                                    when (value) {
                                        "←" -> if (pinCode.isNotEmpty()) {
                                            pinCode = pinCode.dropLast(1)
                                        }
                                        else -> if (pinCode.length < maxPinLength) {
                                            pinCode += value
                                        }
                                    }
                                    if (pinCode.length == maxPinLength) {
                                        if (pinCode == "1234") {
                                            navController.navigate("creating_card")
                                        } else {
                                            pinCode = ""
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .size(80.dp),
                                shape = CircleShape,
                                interactionSource = interactionSource,
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.Black,
                                    containerColor = if (isPressed) Color.Blue else Color(0xFFF5F5F9)
                                )
                            ) {
                                Text(
                                    text = value,
                                    style = TextStyle(fontSize = 34.sp)
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.size(60.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
