package com.example.guessthenumber

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.floor
import kotlin.random.Random


@Composable
fun ResultPage(navController: NavController, success: Boolean = false) {
    val number = (floor(Random.nextDouble(1.0, 11.0))).toInt()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        if (success) {
            Text(
                text = "Congratulations!",
                fontSize = 32.sp,
                fontWeight = FontWeight(800),
                color = Color.Green
            )

            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = "Result",
                modifier = Modifier.size(width = 250.dp, height = 250.dp)
            )

        } else {
            Text(
                text = ":( Try later",
                fontSize = 32.sp,
                fontWeight = FontWeight(800),
                color = Color.Red
            )

            Image(
                painter = painterResource(id = R.drawable.crying),
                contentDescription = "Result",
                modifier = Modifier.size(width = 250.dp, height = 250.dp)
            )
        }


        Column {
            Button(
                onClick = {
                    navController.navigate("homePage") {
                        popUpTo("resultPage") {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.size(width = 250.dp, height = 50.dp)
            ) {
                Text(text = "Home")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("guessPage/$number") {
                        popUpTo("resultPage") {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.size(width = 250.dp, height = 50.dp)
            ) {
                Text(text = "Try Again")
            }
        }
    }
}