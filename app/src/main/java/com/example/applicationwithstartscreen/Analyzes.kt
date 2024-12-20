package com.example.applicationwithstartscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Analyzes(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var activeCategory by remember { mutableStateOf("Популярные") }
    val items = listOf(
        Product("ПЦР-тест на коронавирус", "2 дня (стандартный)", 1800, "Covid"),
        Product("ПЦР на грипп", "1 день (экспресс)", 2500, "Covid"),
        Product("Общий анализ крови", "1 день", 800, "Популярные"),
        Product("Комплекс витаминов", "2 дня", 3000, "Комплексные"),
        Product("Комплекс гормонов", "3 дня", 4500, "Комплексные")
    )

    val filteredItems = items.filter {
        (activeCategory == "Популярные" || it.category == activeCategory) &&
                (it.title.contains(searchText, ignoreCase = true) ||
                        it.description.contains(searchText, ignoreCase = true))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0))
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Поиск анализов") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier.padding(top = 40.dp).width(380.dp).height(60.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color(245, 245, 249),
                containerColor = Color(245, 245, 249),
                focusedTextColor = Color(235, 235, 235),
                unfocusedTextColor = Color(235, 235, 235),
            ),
            shape = RoundedCornerShape(20.dp),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Акции и новости",
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(ScrollState(0))
                .height(200.dp)
                .padding(start = 20.dp),
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.banner_one),
                contentDescription = "Banner Image",
                modifier = Modifier
                    .size(350.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.banner),
                contentDescription = "Banner Image",
                modifier = Modifier
                    .size(350.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Категории анализов",
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(ScrollState(0))
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf("Популярные", "Covid", "Комплексные").forEach { category ->
                Button(
                    onClick = { activeCategory = category },
                    modifier = Modifier
                        .height(55.dp)
                        .width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (activeCategory == category) Color(26, 111, 238) else Color(245, 245, 249)
                    ),
                    shape = RoundedCornerShape(15.dp),
                ) {
                    Text(
                        text = category,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (activeCategory == category) Color.White else Color(126, 126, 154)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            filteredItems.forEach { product ->
                ProductCard(product)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(245, 245, 249)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        ) {
            Text(
                text = product.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                softWrap = true
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = product.description,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${product.price} ₽",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { /* Add Product */ },
            modifier = Modifier
                .padding(end = 5.dp)
                .width(120.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(26, 111, 238)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Добавить",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class Product(
    val title: String,
    val description: String,
    val price: Int,
    val category: String
)
