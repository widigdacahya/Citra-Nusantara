package com.example.citranusantaraandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.citranusantaraandroid.ui.components.screen.ArticleDetailScreen
import com.example.citranusantaraandroid.ui.components.screen.HomeScreen
import com.example.citranusantaraandroid.ui.components.screen.Screen
import com.example.citranusantaraandroid.ui.theme.CitraNusantaraAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CitraNusantaraAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {

        // Homescreen's Route
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        // ArticleDetailScreen's Route
        composable(
            route = Screen.ArticleDetailScreen.route + "/{articleId}",
            arguments = listOf(navArgument("articleId") {
                type = NavType.IntType
                nullable = false
            })
        ) { backStackEntry ->

            val articleId = backStackEntry.arguments?.getInt("articleId")
            ArticleDetailScreen(navController = navController, articleId = articleId)

        }

    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CitraNusantaraAndroidTheme {
        Greeting("Android")
    }
}