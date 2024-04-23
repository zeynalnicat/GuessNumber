package com.example.guessthenumber

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun GuessPage(navController: NavController, number: Int) {


    Log.e("number", number.toString())

    var guess = remember {
        mutableStateOf("")
    }

    var chance = remember {
        mutableStateOf(5)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Chances Left: ${chance.value} ",
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "What is your guess?",
            fontSize = 24.sp,
            fontWeight = FontWeight(900)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = guess.value,
            onValueChange = { guess.value = it },
            label = { Text(text = "Your Guess: ") })

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (guess.value.isBlank() || try {
                        guess.value.toInt(); false
                    } catch (e: NumberFormatException) {
                        true
                    }
                ) {
                }; else if (number == guess.value.toInt()) navController.navigate("resultPage/true") {
                    popUpTo(
                        "guessPage"
                    ) { inclusive = true }

                } else if (chance.value == 1 && guess.value.toInt() != number) {
                    navController
                        .navigate("resultPage/false") {
                            popUpTo("guessPage") {
                                inclusive = true
                            }
                        }
                } else chance.value--
            },
            modifier = Modifier.size(width = 250.dp, height = 50.dp)
        ) {
            Text(text = "Submit")
        }


    }
}

