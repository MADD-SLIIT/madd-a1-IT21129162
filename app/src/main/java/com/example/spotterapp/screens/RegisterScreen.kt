package com.example.spotterapp.screens

import androidx.compose.foundation.layout.* // Import layout components
import androidx.compose.material3.* // Import Material3 components
import androidx.compose.runtime.* // Import state management tools
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
import com.example.spotterapp.models.AuthResponse // Ensure correct import of AuthResponse
import com.example.spotterapp.models.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
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
                text = "Create an Account",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Username field
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Email field
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Password field
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

            Spacer(modifier = Modifier.height(8.dp))

            // Confirm password field
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Register button
            Button(
                onClick = {
                    if (password == confirmPassword) {
                        isLoading = true
                        registerUser(username, password) { response ->
                            isLoading = false
                            if (response?.success == true) {
                                // Navigate to the login screen on successful registration
                                println("Registration successful: ${response.message}")
                                navController.navigate("login")
                            } else {
                                // Show error message
                                errorMessage = response?.message ?: "An error occurred"
                                println("Error: $errorMessage")
                            }
                        }
                    } else {
                        errorMessage = "Passwords do not match"
                        println("Error: $errorMessage")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black, // Black button background
                    contentColor = MaterialTheme.colorScheme.onPrimary // White text
                ),
                enabled = !isLoading
            ) {
                Text(text = if (isLoading) "Registering..." else "Register")
            }

            // Show error message if any
            errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigate to Login button
            TextButton(
                onClick = {
                    navController.navigate("login")
                },
            ) {
                Text("Already have an account? Login", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

// Function to call API and register user
fun registerUser(username: String, password: String, onResult: (AuthResponse?) -> Unit) {
    val apiService = RetrofitInstance.api
    val call = apiService.registerUser(AuthRequest(username, password))

    call.enqueue(object : Callback<AuthResponse> {
        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
            println("API Response: ${response.body()}")
            onResult(response.body())
        }

        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            println("API Error: ${t.message}")
            onResult(null)
        }
    })
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    SpotterAppTheme {
        RegisterScreen(navController = NavController(LocalContext.current))
    }
}
