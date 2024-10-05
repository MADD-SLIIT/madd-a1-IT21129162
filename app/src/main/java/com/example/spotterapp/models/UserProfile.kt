package com.example.spotterapp.models

// Define UserProfile data class
data class UserProfile(
    val username: String,
    val completedExercises: List<String>
)