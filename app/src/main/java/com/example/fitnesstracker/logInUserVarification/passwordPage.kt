package com.example.fitnesstracker.logInUserVarification
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.SharedViewModel
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordPage() {
    // Declare a mutable state to hold the user's password input
    var UserPasswordIn by remember { mutableStateOf("") }
    // Declare a mutable state for the button's text
    var buttonText by remember { mutableStateOf("") }

    // Define the Box layout for the page
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {
        // Display the app logo at the top of the page
        Image(
            painter = painterResource(id = R.drawable.airbus_logo_2001),
            contentDescription = "App Logo",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(0.dp)
                .size(300.dp)
        )

        // Define the Column layout for the form
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello, Aadit",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
            )
            // Display the "Password:" label
            Text(
                text = "Password:",
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 2.dp)
            )
            // Display the password input field
            TextField(
                value = UserPasswordIn,
                onValueChange = { UserPasswordIn = it },
                placeholder = { Text("Enter Password") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shadow(3.dp, RoundedCornerShape(10.dp))
                    .background(Color.White)
            )
            // Display the button's text
            Text(text = buttonText, color = Color.Red)

            // Add some space before the button
            Spacer(modifier = Modifier.height(70.dp))

            // Display the submit button
            Button(
                onClick = { buttonText = if (UserPasswordIn == "Password") "Correct password" else "check password!" },
                colors = ButtonDefaults.buttonColors(Color(192,219,36))
            ) {
                Text(text = "Submit!")
            }
        }
    }
}

// Preview the password page
@Preview(showSystemUi = true)
@Composable
fun PassPrev(){
    PasswordPage()
}