package com.example.applicationwithstartscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.InInspectionModeOnly
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoarding(navController: NavController) {
    var currentPage by remember { mutableStateOf(0) }
    val pagerState =
        rememberPagerState(initialPage = 0)
    Column(
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier
                .weight(1f)
        ) { page ->
            when (page) {
                0 -> Screen1(pagerState, navController)
                1 -> Screen2(pagerState, navController)
                2 -> Screen3(pagerState, navController)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun IndicatordDots(pagerState: PagerState){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        val totalPages = 3
        for (i in 0 until totalPages) {
            val color = if (i == pagerState.currentPage) colorResource(R.color.new_color) else Color.White
            DotIndicator(color = color)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
@Composable
fun DotIndicator(color: Color) {
    Box(
        modifier = Modifier
            .size(25.dp)
            .padding(4.dp)
            .border(width = 2.dp, color = colorResource(R.color.new_color), shape = CircleShape)
            .background(color = color, shape = CircleShape)

    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Screen1(pagerState: PagerState, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 50.dp, bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
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
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.shape),
            contentDescription = "shape",
            modifier = Modifier
                .size(200.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier = Modifier
                .padding(top = 300.dp)
        )
        Text(
            text = "Анализы",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.text_color)
        )
        Spacer(
            modifier = Modifier
                .padding(top = 30.dp)
        )
        Text(
            text = "Экспресс сбор и получение проб",
            fontSize = 20.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier
            .padding(top = 50.dp))
        IndicatordDots(pagerState = pagerState)
        Row(
          modifier = Modifier
              .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.illustration),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.Bottom)
                    .padding(bottom = 50.dp)
            )
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Screen2(pagerState: PagerState, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 50.dp, bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
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
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.shape),
            contentDescription = "shape",
            modifier = Modifier
                .size(200.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .padding(top = 300.dp)
            )
            Text(
                text = "Уведомления",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.text_color)
            )
            Spacer(
                modifier = Modifier
                    .padding(top = 30.dp)
            )
            Text(
                text = "Вы быстро узнаете о результатах",
                fontSize = 20.sp,
                color = Color.Gray
            )
        Spacer(modifier = Modifier
            .padding(top = 50.dp))
        IndicatordDots(pagerState = pagerState)
        Row(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.photo1),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.Bottom)
                    .padding(bottom = 50.dp)
            )
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Screen3(pagerState: PagerState, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 50.dp, bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Завершить",
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
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.shape),
            contentDescription = "shape",
            modifier = Modifier
                .size(200.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .padding(top = 300.dp)
            )
            Text(
                text = "Мониторинг",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.text_color)
            )
            Spacer(
                modifier = Modifier
                    .padding(top = 30.dp)
            )
            Text(
                text = "Наши врачи всегда наблюдают \n" +
                        "за вашими показателями здоровья",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        Spacer(modifier = Modifier
            .padding(top = 50.dp))
        IndicatordDots(pagerState = pagerState)
        Row(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.photo2),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.Bottom)
                    .padding(bottom = 50.dp)
            )
        }
    }
}