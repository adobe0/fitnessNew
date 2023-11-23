package com.example.fitnesstracker.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun addPlanCard(recipeName: String, recipeDescription: String) {
    val isSelected = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .clickable {
                isSelected.value = !isSelected.value
                if (isSelected.value) {
                    addToFirebase(recipeName)
                }
            }
            .background(if (isSelected.value) Color.Green.copy(alpha = 0.3f) else Color.Transparent)
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
            Text(
                text = recipeName,
                modifier = Modifier.align(Alignment.TopStart),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = recipeDescription,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            if (isSelected.value) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    modifier = Modifier.align(Alignment.CenterEnd),
                    tint = Color.Green
                )
            }
        }
    }
}

// Function to add selected recipe to Firebase
private fun addToFirebase(recipeName: String) {
    val database = Firebase.database.reference
    database.child("planned").push().setValue(recipeName)
}

@Preview
@Composable
fun PreviewAddPlanCard() {
    addPlanCard(
        recipeName = "Delicious Pasta",
        recipeDescription = "Creamy and rich..."
    )
}
