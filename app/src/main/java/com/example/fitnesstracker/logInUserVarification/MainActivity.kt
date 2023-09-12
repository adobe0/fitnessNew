package com.example.fitnesstracker.logInUserVarification


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.lifecycle.ViewModelProvider
import com.example.fitnesstracker.R
import com.example.fitnesstracker.SharedViewModel
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        ViewModelProvider(this).get(SharedViewModel::class.java)
        setContent {
            FitnessTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmailPage()

                }
            }
        }
    }
}

val databaseRef = FirebaseDatabase.getInstance().reference
val testDat = databaseRef.child("userInfo/User 2/Name")
var userName: String? = null

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPage() {
    testDat.addListenerForSingleValueEvent(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            userName = snapshot.getValue(String::class.java)
        }

        override fun onCancelled(error: DatabaseError) {
            println("Database error")
        }
    })
    println(userName)
    var UserEmailIn by remember { mutableStateOf("") }
    var buttonText by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {
        Image(
            painter = painterResource(id = R.drawable.airbus_logo_2001),
            contentDescription = "App Logo",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(0.dp)
                .size(300.dp)
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Email ID:",
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 2.dp)
            )
            TextField(
                value = UserEmailIn,
                onValueChange = { UserEmailIn = it },
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
            Text(text = buttonText, color = Color.Red)
            Spacer(modifier = Modifier.height(70

                .dp))
            Button(
                onClick = { buttonText = if (UserEmailIn == userName ) "correct email" else "check email ID!"},
                colors = ButtonDefaults.buttonColors(Color(192,219,36))
            ) {
                Text(text = "Submit!")
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitnessTrackerTheme {
        EmailPage()
    }
}