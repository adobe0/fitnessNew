package com.example.fitnesstracker.logInUserVarification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordReset() {
    // Mutable states to hold the user's new password and confirmed password inputs
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }
    // Mutable state to hold feedback message
    var feedbackMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        // Display the title at the top
        Column {
            Text(
                text = "Reset Password",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 50.dp,start = 50.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Please enter your new password!",
                modifier = Modifier.padding(top = 10.dp, start = 30.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Input field for the new password
            Text(
                text = "New Password:",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 200.dp)
            )
            TextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = { Text("Enter New Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(300.dp)
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shadow(3.dp, RoundedCornerShape(10.dp))
                    .background(Color.White)
            )

            // Input field to confirm the new password
            Text(
                text = "Confirm Password:",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
            TextField(
                value = confirmNewPassword,
                onValueChange = { confirmNewPassword = it },
                placeholder = { Text("Confirm New Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(300.dp)
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shadow(3.dp, RoundedCornerShape(10.dp))
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Display feedback to the user
            Text(text = feedbackMessage, color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(20.dp)) // Aesthetic distance

            Button(
                onClick = {
                    // Check if new password and confirmed password match
                    if (newPassword == confirmNewPassword && newPassword.isNotEmpty()) {
                        feedbackMessage = "Password reset successful!"
                    } else {
                        feedbackMessage = "Passwords do not match or are empty."
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(192, 219, 36))
            ) {
                Text(text = "Reset Password")
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun PasRes(){
    PasswordReset()
}