package com.example.fitnesstracker.MainPages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandPage() {
    Column {
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
                // Define the action to be taken when the button is clicked
                //navigate to another screen or perform some action.
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),


        ) {
            Text(text = "Add food")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun look(){
    LandPage()
}