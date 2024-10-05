package com.example.spotterapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.spotterapp.ui.theme.SpotterAppTheme
import com.example.spotterapp.ui.theme.Black
import com.example.spotterapp.ui.theme.LightRed
import com.example.spotterapp.models.ApiService
import com.example.spotterapp.models.AuthRequest
import com.example.spotterapp.models.AuthResponse // Ensure this is the correct import
import com.example.spotterapp.models.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

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
                text = "Login to Spotter",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username or Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Login button
            Button(
                onClick = {
                    isLoading = true
                    loginUser(username, password) { response ->
                        isLoading = false
                        if (response?.success == true) {
                            // Navigate to home screen on successful login
                            navController.navigate("home")
                        } else {
                            // Display error message on failed login
                            errorMessage = response?.message ?: "Login failed"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                enabled = !isLoading
            ) {
                Text(text = if (isLoading) "Logging in..." else "Login", style = MaterialTheme.typography.labelLarge)
            }

            // Display error message if login fails
            errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    // Navigate to a register screen when the "Sign Up" button is clicked
                    navController.navigate("register")
                },
            ) {
                Text("Don't have an account? Sign up", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

// Function to log in the user via the API
fun loginUser(username: String, password: String, onResult: (AuthResponse?) -> Unit) {
    val apiService = RetrofitInstance.api
    val call = apiService.loginUser(AuthRequest(username, password))

    call.enqueue(object : Callback<AuthResponse> {
        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
            onResult(response.body())
        }

        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            onResult(null)
        }
    })
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    SpotterAppTheme {
        LoginScreen(navController = NavController(LocalContext.current))
    }
}
