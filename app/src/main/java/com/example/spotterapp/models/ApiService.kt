package com.example.spotterapp.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// AuthRequest data class for authentication requests (used in login/register)
data class AuthRequest(val username: String, val password: String)

// User data class to represent the user object returned by the API
data class User(
    val _id: String,  // User ID from the backend
    val username: String,  // Username of the user
    val completedExercises: List<String>  // List of exercises the user has completed
)

// AuthResponse data class for handling responses from login/register endpoints
data class AuthResponse(
    val message: String,  // Response message from the backend (e.g., success or failure message)
    val success: Boolean  // Indicates if the operation was successful
)

interface ApiService {

    // Register a new user
    @POST("users/register")
    fun registerUser(@Body request: AuthRequest): Call<AuthResponse>

    // Login an existing user
    @POST("users/login")
    fun loginUser(@Body request: AuthRequest): Call<AuthResponse>

    // Get user profile by username
    @GET("users/{username}")
    fun getUserProfile(@Path("username") username: String): Call<User>

    // Update user's completed exercises
    @PUT("users/{username}/completed")
    fun updateCompletedExercises(@Path("username") username: String, @Body exerciseName: String): Call<User>
}

