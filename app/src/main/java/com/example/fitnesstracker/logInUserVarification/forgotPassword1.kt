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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordSecurity() {
    // Mutable state to hold the user's security answer input
    var securityAnswer by remember { mutableStateOf("") }
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
                text = "Security Check",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 50.dp, start = 55.dp)
            )
            Text(
                text = "Please answer the question!",
                modifier = Modifier.padding(start = 100.dp)
            )
        }

        // Display the security question
        Column {
            Text(
                text = "What was the name of your first pet?",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 40.dp, top = 200.dp)
            )

            // Input field for the security answer
            Text(text = "Answer:",
                modifier = Modifier.align(Alignment.Start).padding(top = 100.dp, start = 40.dp))

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



            // Display feedback to the user
            Text(text = feedbackMessage, color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(50.dp)) // Aesthetic distance

            Button(
                onClick = {
                    // Check if security answer is not empty
                    feedbackMessage = if (securityAnswer.isNotEmpty()) {
                        "Input detected."
                    } else {
                        "Input didn't match!"
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




@Preview(showSystemUi =  true)
@Composable
fun SecPage(){
    ForgotPasswordSecurity()
}
