package com.example.spotterapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spotterapp.models.UserProfile
import com.example.spotterapp.ui.theme.SpotterAppTheme
import com.example.spotterapp.ui.theme.LightRed

@Composable
fun ProfileScreen(userProfile: UserProfile) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightRed
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Display username at the top
            Text(
                text = "Username: ${userProfile.username}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Section for recently completed exercises
            Text(
                text = "Recently Completed Exercises:",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (userProfile.completedExercises.isNotEmpty()) {
                LazyColumn {
                    items(userProfile.completedExercises.size) { index ->
                        Text(
                            text = "- ${userProfile.completedExercises[index]}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            } else {
                Text(
                    text = "No exercises completed yet.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val sampleUser = UserProfile(
        username = "JohnDoe",
        completedExercises = listOf("Push-Up", "Jumping Jacks")
    )
    SpotterAppTheme {
        ProfileScreen(userProfile = sampleUser)
    }
}