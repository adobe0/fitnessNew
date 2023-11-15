package com.example.fitnesstracker.logInUserVarification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.screen
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordSecurity(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var securityAnswer by remember { mutableStateOf("") }
    var securityQuestion by remember { mutableStateOf("Loading question...") }
    var feedbackMessage by remember { mutableStateOf("") }

    val databaseRef = FirebaseDatabase.getInstance().reference

    LaunchedEffect(username) {
        if (username.isNotEmpty()) {
            databaseRef.child("users").child(username).get().addOnSuccessListener {
                val userDetail = it.getValue<Map<String, Any>>()
                securityQuestion = userDetail?.get("securityQuestion") as? String ?: "No question found."
            }.addOnFailureListener {
                securityQuestion = "Error fetching question."
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        // Column for layout
        Column {
            // Title and instruction
            Text(
                text = "Security Check",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 50.dp, start = 55.dp)
            )
            Text(
                text = "Please enter your username and answer the question!",
                modifier = Modifier.padding(start = 100.dp)
            )

            // Username input
            Text(
                text = "Username:",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 40.dp, top = 150.dp)
            )
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Enter Username") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(300.dp)
                    .padding(top = 2.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shadow(3.dp, RoundedCornerShape(10.dp))
                    .background(Color.White)
            )

            // Security question display
            Text(
                text = securityQuestion,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 40.dp, top = 20.dp)
            )

            // Security answer input
            Text(text = "Answer:",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 10.dp, start = 40.dp))
            TextField(
                value = securityAnswer,
                onValueChange = { securityAnswer = it },
                placeholder = { Text("Enter Answer") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(300.dp)
                    .padding(top = 2.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shadow(3.dp, RoundedCornerShape(10.dp))
                    .background(Color.White)
            )

            // Feedback message
            Text(text = feedbackMessage, color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(50.dp))

            // Button to continue after answering
            Button(
                onClick = {
                    // Check if security answer is correct
                    databaseRef.child("users").child(username).get().addOnSuccessListener {
                        val userDetail = it.getValue<Map<String, Any>>()
                        val storedAnswer = userDetail?.get("securityAnswer") as? String
                        if (securityAnswer == storedAnswer) {
                            navController.navigate(route = screen.forgotpass2.route)
                        } else {
                            feedbackMessage = "Incorrect answer!"
                        }
                    }.addOnFailureListener {
                        feedbackMessage = "Error verifying answer."
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(192, 219, 36)),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SecPage(){
    ForgotPasswordSecurity(rememberNavController())
}
