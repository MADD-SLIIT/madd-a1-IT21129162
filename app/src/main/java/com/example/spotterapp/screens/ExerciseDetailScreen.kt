package com.example.spotterapp.screens

import androidx.compose.foundation.layout.* // Import layout components
import androidx.compose.material3.* // Import Material3 components
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController // NavController for navigation
import androidx.compose.ui.platform.LocalContext
import com.example.spotterapp.ui.theme.SpotterAppTheme
import com.example.spotterapp.ui.theme.Black
import com.example.spotterapp.ui.theme.LightRed

@Composable
fun ExerciseDetailScreen(
    exerciseName: String,
    navController: NavController,
    onComplete: () -> Unit // Callback to be triggered when the exercise is marked as complete
) {
    // Instructions for each exercise
    val instructions = when (exerciseName) {
        "Jumping Jacks" -> listOf(
            "Step 1: Stand upright with your legs together, arms at your sides.",
            "Step 2: Bend your knees slightly, then jump into the air.",
            "Step 3: Spread your legs shoulder-width apart, and stretch your arms out and over your head."
        )
        "Bodyweight Squat" -> listOf(
            "Step 1: Start with your feet shoulder-width apart.",
            "Step 2: Lower your body into a squat.",
            "Step 3: Push back up to the starting position."
        )
        "Push-Up" -> listOf(
            "Step 1: Start in a plank position with your arms straight.",
            "Step 2: Lower your body until your chest nearly touches the floor.",
            "Step 3: Push yourself back up to the starting position."
        )
        "Chest Press" -> listOf(
            "Step 1: Lie flat on your back on a bench with a dumbbell in each hand.",
            "Step 2: Push the dumbbells up until your arms are fully extended.",
            "Step 3: Slowly lower the dumbbells back down to your chest."
        )
        "Lunges" -> listOf(
            "Step 1: Stand upright with your feet together.",
            "Step 2: Take a step forward with one foot and lower your hips until both knees are at 90-degree angles.",
            "Step 3: Push through the heel of your front foot to return to the starting position."
        )
        "Shoulder Press" -> listOf(
            "Step 1: Stand with your feet shoulder-width apart.",
            "Step 2: Hold dumbbells at shoulder height, palms facing forward.",
            "Step 3: Push the dumbbells overhead until your arms are fully extended."
        )
        // Add other exercises here...
        else -> listOf("No instructions available for this exercise.")
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
            Text(
                text = exerciseName,
                style = MaterialTheme.typography.titleLarge // Use titleLarge style
            )
            Spacer(modifier = Modifier.height(16.dp))

            instructions.forEach { step ->
                Text(
                    text = step,
                    style = MaterialTheme.typography.bodyLarge // Use bodyLarge style
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Call the onComplete callback to mark this exercise as completed
                    onComplete()

                    // Navigate to the MotivationalQuoteScreen with the timer
                    navController.navigate("motivationalQuote")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,  // Black button background
                    contentColor = MaterialTheme.colorScheme.onPrimary // White text
                )
            ) {
                Text(text = "Start Exercise", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseDetailScreenPreview() {
    SpotterAppTheme {
        ExerciseDetailScreen(
            exerciseName = "Chest Press",
            navController = NavController(LocalContext.current),
            onComplete = {}
        ) // Mocked onComplete callback and NavController for preview
    }
}
