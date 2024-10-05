package com.example.spotterapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun ExerciseListScreen(navController: NavController, muscleGroup: String) {
    // Exercise data categorized by muscle group
    val exercises = when (muscleGroup) {
        "Legs" -> listOf("Jumping Jacks", "Bodyweight Squat", "Lunges", "Plank")
        "Chest" -> listOf("Push-Up", "Chest Press", "Chest Fly", "Decline Push-Up")
        "Shoulders" -> listOf("Shoulder Press", "Lateral Raise", "Front Raise", "Arnold Press")
        else -> listOf("Exercise 1", "Exercise 2") // Default fallback exercises
    }

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
            Text(text = "$muscleGroup Exercises", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(exercises.size) { index ->
                    Button(
                        onClick = {
                            // Navigate to exercise detail screen, passing the exercise name
                            navController.navigate("exerciseDetail/${exercises[index]}")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Black,  // Black button background
                            contentColor = MaterialTheme.colorScheme.onPrimary // White text
                        )
                    ) {
                        Text(text = exercises[index], style = MaterialTheme.typography.labelLarge)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseListScreenPreview() {
    SpotterAppTheme {
        ExerciseListScreen(
            navController = NavController(LocalContext.current),
            muscleGroup = "Legs"
        )
    }
}
