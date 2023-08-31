package com.example.fitnesstracker.MainPages

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreenContent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top Bar with smaller Hamburger Menu at the top-left corner
        TopAppBar(
            title = { },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.noun_hamburger_menu_clicked_4252769),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(16.dp)
                )
            },
            modifier = Modifier.align(Alignment.TopStart),
        )

        // Your content here (e.g., list of foods, recipes, etc.)

        // Floating Action Button for "Add Food" at the bottom-right
        FloatingActionButton(
            onClick = { /* Do something when clicked */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Add Food"
            )
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun MainPage(){
    MyScreenContent()
}
