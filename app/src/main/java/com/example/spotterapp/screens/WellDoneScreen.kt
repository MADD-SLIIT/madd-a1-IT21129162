package com.example.spotterapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.spotterapp.ui.theme.SpotterAppTheme
import com.example.spotterapp.ui.theme.Black
import com.example.spotterapp.ui.theme.LightRed

@Composable
fun WellDoneScreen(navController: NavController) {
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
            Text(
                text = "Well Done!",
                style = MaterialTheme.typography.titleLarge // Use titleLarge for the message
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Button to navigate back to the Home screen or start another workout
            Button(
                onClick = {
                    // Navigate back to Home or another screen
                    navController.navigate("home") // Example: navigate back to HomeScreen
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black, // Black button background
                    contentColor = MaterialTheme.colorScheme.onPrimary // White text
                )
            ) {
                Text(text = "Go to Home", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellDoneScreenPreview() {
    SpotterAppTheme {
        WellDoneScreen(navController = NavController(LocalContext.current))
    }
}
