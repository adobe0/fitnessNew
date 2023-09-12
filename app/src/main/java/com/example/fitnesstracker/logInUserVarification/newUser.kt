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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R
import com.google.firebase.database.FirebaseDatabase
import org.mindrot.jbcrypt.BCrypt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpPage() {
    var userNameIn by remember { mutableStateOf("") }
    var userEmailIn by remember { mutableStateOf("") }
    var userPasswordIn by remember { mutableStateOf("") }
    var userConfirmPasswordIn by remember { mutableStateOf("") }
    var buttonText by remember { mutableStateOf("") }
    val UserEnter = databaseRef.child("users")
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {

        Image(
            painter = painterResource(id = R.drawable.airbus_logo_2001),
            contentDescription = "App Logo",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(1.dp) // Padding from the top and end
                .size(70.dp)   // Reduced the size for top-right placement
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Believe in yourself and\nall that you are.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp)) // Spacing between the quote and the fields
            Text(
                text = "Name:",
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 2.dp)
            )
            TextField(
                value = userNameIn,
                onValueChange = { userNameIn = it },
                placeholder = { Text("Enter Name") },
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

            Text(
                text = "Email ID:",
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 2.dp)
            )
            TextField(
                value = userEmailIn,
                onValueChange = { userEmailIn = it },
                placeholder = { Text("Enter Email") },
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

            Text(
                text = "Password:",
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 2.dp)
            )
            TextField(
                value = userPasswordIn,
                onValueChange = { userPasswordIn = it },
                placeholder = { Text("Enter Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(), // This masks the password as dots
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // This will show a password-style keyboard
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

            Text(
                text = "Confirm Password:",
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 2.dp)
            )
            TextField(
                value = userConfirmPasswordIn,
                onValueChange = { userConfirmPasswordIn = it },
                placeholder = { Text("Re-enter Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(), // This masks the password as dots
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // This will show a password-style keyboard
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

            Text(text = buttonText, color = Color.Red)

            Spacer(modifier = Modifier.height(70.dp))

            Button(
                onClick = {
                    if (userPasswordIn == userConfirmPasswordIn) {
                        val secPassword = BCrypt.hashpw(userPasswordIn, BCrypt.gensalt())
                        // Saving data to Firebase

                        val userDetail = hashMapOf(
                            "email" to userEmailIn,
                            "password" to secPassword
                        )

                        val database = FirebaseDatabase.getInstance()
                        val userReference = UserEnter.child(userNameIn)

                        userReference.setValue(userDetail)
                            .addOnSuccessListener {
                                buttonText = "Successfully registered!"
                            }
                            .addOnFailureListener {
                                buttonText = "Registration failed. Please try again."
                            }
                    } else {
                        buttonText = "Passwords do not match!"
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(192,219,36))
            ) {
                Text(text = "Sign Up!")
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Preview(){
    SignUpPage()
}
