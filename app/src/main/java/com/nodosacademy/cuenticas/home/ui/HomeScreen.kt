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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.nodosacademy.cuenticas.R
import com.nodosacademy.cuenticas.Util.toCOPMoney
import com.nodosacademy.cuenticas.data.MoneyMovement
import com.nodosacademy.cuenticas.data.MovementType

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeScreenUIState: HomeScreenUIState,
    onEvent: (HomeScreenEvent) -> Unit
) {
    var shouldShowDialog by remember { mutableStateOf(false) }
    if (shouldShowDialog) {
        AddMovementDialog(
            onDismissRequest = { shouldShowDialog = false },
            onConfirmation = {
                shouldShowDialog = false
                onEvent(
                    HomeScreenEvent.OnAddExpense(
                        MoneyMovement(
                            1,
                            "Test",
                            it,
                            false,
                            MovementType.Food
                        )
                    )
                )
            },
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            imageDescription = "This is a dialog with buttons and an image."
        )
    }
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
            TopBar(
                modifier = Modifier.fillMaxWidth(),
                homeScreenUIState.userName,
                homeScreenUIState.userId
            )
            Spacer(modifier = Modifier.height(32.dp))
            Balance(modifier = Modifier, homeScreenUIState.balance)
            Spacer(modifier = Modifier.height(32.dp))
            ButtonsRow(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                onAddExpense = {
                    shouldShowDialog = true
                },
                onAddIncome = {
                    shouldShowDialog = true
                }
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
                    Text(text = "Movimientos", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Ver todos", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(homeScreenUIState.list) { item ->
                        MovementItem(
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
fun MovementItem(modifier: Modifier, moneyMovement: MoneyMovement) {
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
                painter = painterResource(id = moneyMovement.expenseType.iconResId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = moneyMovement.title, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            if (moneyMovement.isIncome) {
                Text(text = moneyMovement.value.toCOPMoney(), fontSize = 20.sp)
            } else {
                Text(
                    text = "-${moneyMovement.value.toCOPMoney()}",
                    fontSize = 20.sp,
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
fun ButtonsRow(modifier: Modifier, onAddExpense: () -> Unit, onAddIncome: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeButton(
            modifier = Modifier.width(80.dp),
            iconResInt = R.drawable.baseline_arrow_upward_24,
            text = "Gasto"
        ) { onAddExpense() }
        HomeButton(
            modifier = Modifier.width(80.dp),
            iconResInt = R.drawable.baseline_arrow_downward_24,
            text = "Ingreso"
        ) { onAddIncome() }
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
fun Balance(modifier: Modifier, balance: Double = 0.0) {
    Column(modifier = modifier) {
        Text(
            text = "Disponible",
            fontSize = 32.sp,
            color = Color.White.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold
        )
        Text(text = balance.toCOPMoney(), fontSize = 48.sp, color = Color.White)
    }
}

@Composable
fun TopBar(modifier: Modifier, userName: String, userId: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserSection(Modifier.height(60.dp), userName, userId)
        NotificationIcon(
            Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}

@Composable
fun NotificationIcon(modifier: Modifier, hasNotification: Boolean = true) {
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
        if (hasNotification) {
            NotificationBadge(Modifier.align(Alignment.TopEnd))
        }
    }
}

@Composable
fun NotificationBadge(modifier: Modifier) {
    Canvas(modifier = modifier.size(10.dp)) {
        drawCircle(color = Color.Yellow)
    }
}

@Composable
fun UserSection(modifier: Modifier, userName: String = "Lukas", userId: String = "*0456") {
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
            Text(text = userName, color = Color.White)
            Text(text = userId, color = Color.White)
        }
    }

}

@Composable
fun AddMovementDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (Double) -> Unit,
    painter: Painter,
    imageDescription: String,
) {
    var text by remember { mutableStateOf("") }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Añadir movimiento")
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    maxLines = 1,
                    value = text, onValueChange = {
                        text = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Omitir")
                    }
                    TextButton(
                        onClick = { onConfirmation(text.toDouble()) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Agregar")
                    }
                }
            }
        }
    }
}


/*@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        HomeScreenUIState(
            userName = "Lukas",
            userId = "*0456",
            balance = 100000.0,
            hasNotification = true
        )
    )
}*/


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