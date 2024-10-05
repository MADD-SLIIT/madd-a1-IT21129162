package com.example.spotterapp.screens

import androidx.compose.foundation.layout.* // Import for layout
import androidx.compose.material3.* // Import for Material3 components
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext // Ensure this import for LocalContext is added
import com.example.spotterapp.ui.theme.SpotterAppTheme
import com.example.spotterapp.ui.theme.Black
import com.example.spotterapp.ui.theme.LightRed
import com.example.spotterapp.models.UserProfile

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightRed // Set the light red background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Start sweating!", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Navigate to the Muscle Group Selection Screen
                    navController.navigate("muscleGroupSelection")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black, // Black button background
                    contentColor = MaterialTheme.colorScheme.onPrimary // White text
                )
            ) {
                Text(text = "View Workouts", style = MaterialTheme.typography.labelLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Add a button to navigate to the ProfileScreen
            Button(
                onClick = {
                    // Navigate to the ProfileScreen, passing the profile information
                    navController.navigate("profile")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black, // Black button background
                    contentColor = MaterialTheme.colorScheme.onPrimary // White text
                )
            ) {
                Text(text = "Profile", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SpotterAppTheme {
        HomeScreen(navController = NavController(LocalContext.current)) // Corrected LocalContext import
    }
}
