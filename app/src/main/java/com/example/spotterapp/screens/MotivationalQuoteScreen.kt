package com.example.spotterapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.spotterapp.ui.theme.SpotterAppTheme
import com.example.spotterapp.ui.theme.Black
import com.example.spotterapp.ui.theme.LightRed
import kotlinx.coroutines.delay

@Composable
fun MotivationalQuoteScreen(navController: NavController) {
    // Timer state
    var timeInSeconds by remember { mutableStateOf(0) }

    // Start the timer using LaunchedEffect
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L) // Delay by 1 second
            timeInSeconds += 1
        }
    }

    // Format the timer
    val formattedTime = String.format("%02d:%02d", timeInSeconds / 60, timeInSeconds % 60)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightRed // Light red background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Motivational quote
            Text(
                text = "Push harder!",
                style = MaterialTheme.typography.titleLarge // Use titleLarge style for the quote
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display the timer
            Text(
                text = formattedTime,
                style = MaterialTheme.typography.titleLarge // Timer style
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Button to end the exercise
            Button(
                onClick = {
                    // Navigate to Well Done screen
                    navController.navigate("wellDone")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black, // Black button background
                    contentColor = MaterialTheme.colorScheme.onPrimary // White text
                )
            ) {
                Text(text = "End Exercise", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MotivationalQuoteScreenPreview() {
    SpotterAppTheme {
        MotivationalQuoteScreen(navController = NavController(LocalContext.current))
    }
}
