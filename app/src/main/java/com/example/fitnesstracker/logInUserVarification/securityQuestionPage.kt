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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityQuestionPage() {
    var securityQuestion by remember { mutableStateOf("") }
    var securityAnswer by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {

        Image(
            painter = painterResource(id = R.drawable.airbus_logo_2001),
            contentDescription = "App Logo",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(3.dp)
                .size(60.dp)
        )
        Text(text = "Lets get your account secured!!",
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp,
            modifier = Modifier.padding(top = 200.dp, start = 30.dp,end = 30.dp))

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Security Question:",
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 2.dp)
            )
            TextField(
                value = securityQuestion,
                onValueChange = { securityQuestion = it },
                placeholder = { Text("Enter your security question") },
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
                text = "Security Answer:",
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 2.dp)
            )
            TextField(
                value = securityAnswer,
                onValueChange = { securityAnswer = it },
                placeholder = { Text("Enter your security answer") },
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

            Spacer(modifier = Modifier.height(70.dp))

            Button(
                onClick = {
                    // Handle the submission or move to the next step here
                },
                colors = ButtonDefaults.buttonColors(Color(192,219,36))
            ) {
                Text(text = "Submit!")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SecQuestion(){
    SecurityQuestionPage()
}
