package com.nodosacademy.cuenticas.home.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nodosacademy.cuenticas.ExpenseType
import com.nodosacademy.cuenticas.R
import com.nodosacademy.cuenticas.data.Expense

@Composable
fun HomeScreen(modifier: Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.home_background),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(
            modifier = modifier
                .background(color = Color.Black.copy(alpha = 0.4f))
                .padding(16.dp)
        ) {
            TopBar(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(32.dp))
            Balance(modifier = Modifier)
            Spacer(modifier = Modifier.height(32.dp))
            ButtonsRow(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
            )
        }
        Card(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            shape = RoundedCornerShape(
                bottomStart = 0.dp,
                bottomEnd = 0.dp,
                topStart = 16.dp,
                topEnd = 16.dp
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Gastos", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Ver todos", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    val items = arrayListOf(
                        Expense(1, "Charity", 32000.0, ExpenseType.Food()),
                        Expense(1, "Charity", 32000.0, ExpenseType.Food()),
                        Expense(1, "Charity", 32000.0, ExpenseType.Food()),
                    )
                    items(items) { item ->
                        ExpenseItem(
                            modifier
                                .fillMaxHeight()
                                .padding(vertical = 32.dp),
                            item
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExpenseItem(modifier: Modifier, expense: Expense) {
    Card(
        modifier = modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = expense.expenseType.iconResId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = expense.title, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$ ${expense.value}", fontSize = 24.sp)
        }
    }
}

@Composable
fun ButtonsRow(modifier: Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        HomeButton(
            modifier = Modifier.width(80.dp),
            iconResInt = R.drawable.baseline_arrow_upward_24,
            text = "Gasto"
        ) {

        }
        HomeButton(
            modifier = Modifier.width(80.dp),
            iconResInt = R.drawable.baseline_arrow_downward_24,
            text = "Ingreso"
        ) {

        }
    }
}

@Composable
fun HomeButton(modifier: Modifier, iconResInt: Int, text: String, onClick: () -> Unit) {
    Column(
        modifier = modifier.clickable {
            onClick()
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color.White.copy(alpha = 0.3f))
                .padding(6.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(3.dp),
                painter = painterResource(id = iconResInt),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
        Text(text = text, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }

}

@Composable
fun Balance(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Balance",
            fontSize = 32.sp,
            color = Color.White.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold
        )
        Text(text = "$1,924.3", fontSize = 64.sp, color = Color.White)
    }
}

@Composable
fun TopBar(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserSection(Modifier.height(60.dp))
        NotificationIcon(
            Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}

@Composable
fun NotificationIcon(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(color = Color.Gray.copy(alpha = 0.3f))
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.baseline_notifications_24),
            contentDescription = null,
            tint = Color.White
        )
        NotificationBadge(Modifier.align(Alignment.TopEnd))
    }
}

@Composable
fun NotificationBadge(modifier: Modifier) {
    Canvas(modifier = modifier.size(10.dp)) {
        drawCircle(color = Color.Yellow)
    }
}

@Composable
fun UserSection(modifier: Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Lukas", color = Color.White)
            Text(text = "*0456", color = Color.White)
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.fillMaxSize()
    )
}


@Composable
fun ButtonsPreview() {
    HomeButton(
        modifier = Modifier
            .width(60.dp),
        iconResInt = R.drawable.ic_launcher_foreground,
        onClick = {},
        text = "Test Icon"
    )
}