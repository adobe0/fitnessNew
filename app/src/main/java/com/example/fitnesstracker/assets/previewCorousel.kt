package com.example.fitnesstracker.assets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.fitnesstracker.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun RecipePreviewCard(
    imageUrl: Int,
    title: String,
    description: String,
    icons: List<Int>,
    ingredients: List<String> // Keep this even if it's not used directly
) {
    var showDialog by remember { mutableStateOf(false) }
    var instructions by remember { mutableStateOf<List<String>>(emptyList()) }
    var ingredientsFromDb by remember { mutableStateOf<List<String>>(emptyList()) }

    // Fetch instructions and ingredients from Firebase when the dialog is shown
    LaunchedEffect(showDialog) {
        if (showDialog) {
            val database = Firebase.database.reference
            database.child("recipes").child(title).child("instructions").get()
                .addOnSuccessListener { dataSnapshot ->
                    instructions = dataSnapshot.children.mapNotNull { it.getValue(String::class.java) }
                }
                .addOnFailureListener {
                    instructions = listOf("Failed to load instructions.")
                }
            database.child("recipes").child(title).child("ingredients").get()
                .addOnSuccessListener { dataSnapshot ->
                    ingredientsFromDb = dataSnapshot.children.mapNotNull { it.getValue(String::class.java) }
                }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp)
            .clickable { showDialog = true }
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column {
            Image(
                painter = painterResource(id = imageUrl),
                contentDescription = "Recipe Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    icons.forEach { iconRes ->
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light
            )
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                modifier = Modifier.fillMaxSize().fillMaxWidth(),
                color = Color.White
            ) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
                    Image(
                        painter = painterResource(id = imageUrl),
                        contentDescription = "Recipe Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(250.dp)
                    )
                    Text("Title: $title", style = MaterialTheme.typography.titleLarge)
                    Text("Description: $description", style = MaterialTheme.typography.bodySmall)

                    // Icons row
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        icons.forEach { iconRes ->
                            Icon(
                                painter = painterResource(id = iconRes),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp).padding(end = 8.dp)
                            )
                        }
                    }

                    // Ingredients section
                    Text("Ingredients", style = MaterialTheme.typography.bodyLarge)
                    ingredientsFromDb.forEach { ingredient ->
                        Text("- $ingredient", style = MaterialTheme.typography.bodyMedium)
                    }

                    // Instructions section
                    Text("Instructions", style = MaterialTheme.typography.headlineMedium)
                    instructions.forEach { instruction ->
                        Text(instruction, style = MaterialTheme.typography.bodyMedium)
                    }

                    // Close button
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        modifier = Modifier.align(Alignment.End),
                        onClick = { showDialog = false }
                    ) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun RecipeCardSample() {
    RecipePreviewCard(
        imageUrl = R.drawable.sample_card,
        title = "Delicious Pasta",
        description = "Delve into the rich and creamy flavors...",
        icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298),
        ingredients = listOf("Pasta", "Tomato Sauce", "Basil", "Cheese")
    )
}


