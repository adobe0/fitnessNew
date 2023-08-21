package com.example.fitnesstracker.MainPages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun LandingPage() {
    Column {
        Text(
            text = "hello",
            fontSize = 100.sp
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun FuckVansh(){
    LandingPage()
}
