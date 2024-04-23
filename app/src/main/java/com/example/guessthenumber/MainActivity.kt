package com.example.guessthenumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.guessthenumber.ui.theme.GuessTheNumberTheme
import kotlin.math.floor
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheNumberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {

    val navControl = rememberNavController()
    NavHost(navController = navControl, startDestination = "homePage") {
        composable("homePage") {
            HomePage(navControl)
        }
        composable("guessPage/{number}", arguments = listOf(
            navArgument("number") { type = NavType.IntType }
        )) {
            val number = it.arguments?.getInt("number",0)!!
            GuessPage(navControl, number)
        }
        composable("resultPage/{result}", arguments = listOf(
            navArgument("result") { type = NavType.BoolType }
        )) {
            val result = it.arguments?.getBoolean("result", false)!!
            ResultPage(navController = navControl, success = result)
        }
    }
}

@Composable
fun HomePage(navController: NavController) {

    val number = (floor(Random.nextDouble(1.0, 11.0))).toInt()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        ) {


        Text(text = "Guess the number", fontSize = 24.sp, fontWeight = FontWeight(900))
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { navController.navigate("guessPage/$number") },
            Modifier.size(width = 250.dp, height = 50.dp)
        ) {
            Text(text = "Start")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuessTheNumberTheme {
//        HomePage()
    }
}