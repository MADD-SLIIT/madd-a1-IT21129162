package com.example.spotterapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.spotterapp.ui.theme.SpotterAppTheme
import com.example.spotterapp.ui.theme.Black
import com.example.spotterapp.ui.theme.LightRed

@Composable
fun MuscleGroupSelectionScreen(navController: NavController) {
    // List of muscle groups
    val muscleGroups = listOf("Legs", "Chest", "Shoulders")

    // Apply background color using Surface
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightRed // Set the light red background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Select a Muscle Group",
                style = MaterialTheme.typography.titleLarge // Use titleLarge for header
            )
            Spacer(modifier = Modifier.height(16.dp))

            muscleGroups.forEach { muscleGroup ->
                Button(
                    onClick = {
                        // Navigate to ExerciseListScreen, passing the selected muscle group
                        navController.navigate("exerciseList/$muscleGroup")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Black, // Black button background
                        contentColor = MaterialTheme.colorScheme.onPrimary // White text
                    )
                ) {
                    Text(text = muscleGroup, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MuscleGroupSelectionScreenPreview() {
    SpotterAppTheme {
        MuscleGroupSelectionScreen(navController = NavController(LocalContext.current))
    }
}
