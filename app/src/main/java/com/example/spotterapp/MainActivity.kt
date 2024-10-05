package com.example.spotterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotterapp.screens.*
import com.example.spotterapp.ui.theme.SpotterAppTheme
import com.example.spotterapp.models.UserProfile // Ensure UserProfile is imported

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpotterApp()
        }
    }
}

@Composable
fun SpotterApp() {
    // Initialize the user profile and remember the completed exercises state
    var completedExercises by remember { mutableStateOf(listOf<String>()) }

    // Sample user data
    val userProfile = UserProfile(
        username = "JohnDoe",
        completedExercises = completedExercises
    )

    SpotterAppTheme {
        val navController = rememberNavController()
        SpotterAppNavigation(
            navController = navController,
            userProfile = userProfile,
            onExerciseComplete = { exerciseName ->
                completedExercises = completedExercises + exerciseName
            }
        )
    }
}

@Composable
fun SpotterAppNavigation(
    navController: NavHostController,
    userProfile: UserProfile, // Pass the UserProfile object properly
    onExerciseComplete: (String) -> Unit
) {
    NavHost(navController = navController, startDestination = "login") {
        // Login Screen
        composable("login") {
            LoginScreen(navController = navController)
        }

        // Register Screen
        composable("register") {
            RegisterScreen(navController = navController)
        }

        // Home Screen
        composable("home") {
            HomeScreen(navController = navController)
        }

        // Muscle Group Selection Screen
        composable("muscleGroupSelection") {
            MuscleGroupSelectionScreen(navController = navController)
        }

        // Exercise List Screen for a specific muscle group
        composable("exerciseList/{muscleGroup}") { backStackEntry ->
            val muscleGroup = backStackEntry.arguments?.getString("muscleGroup") ?: "None"
            ExerciseListScreen(
                navController = navController,
                muscleGroup = muscleGroup
            )
        }

        // Exercise Detail Screen for a specific exercise
        composable("exerciseDetail/{exerciseName}") { backStackEntry ->
            val exerciseName = backStackEntry.arguments?.getString("exerciseName") ?: "Exercise"
            ExerciseDetailScreen(
                exerciseName = exerciseName,
                navController = navController,
                onComplete = {
                    onExerciseComplete(exerciseName)
                }
            )
        }

        // Motivational Quote Screen
        composable("motivationalQuote") {
            MotivationalQuoteScreen(navController = navController)
        }

        // Well Done Screen
        composable("wellDone") {
            WellDoneScreen(navController = navController)
        }

        // Profile Screen, pass the user profile with completed exercises
        composable("profile") {
            ProfileScreen(userProfile = userProfile)
        }
    }
}