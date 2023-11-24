package com.example.fitnesstracker.MainPages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.R
import com.example.fitnesstracker.assets.RecipePreviewCard
import com.example.fitnesstracker.screen
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

data class Recipe(
    val imageUrl: Int,
    val title: String,
    val description: String,
    val icons: List<Int>,
    val ingredients: List<String>,
    val instructions: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandPage(navController: NavController) {
    val recipes = remember { mutableStateOf<List<Recipe>>(emptyList()) }

    LaunchedEffect(Unit) {
        val database = Firebase.database.reference
        database.child("recipes").get().addOnSuccessListener { dataSnapshot ->
            val fetchedRecipes = dataSnapshot.children.mapNotNull { snapshot ->
                val description = snapshot.child("description").getValue(String::class.java)
                val name = snapshot.child("name").getValue(String::class.java)
                val imageUrl = R.drawable.sample_card // Placeholder image
                val icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298) // Placeholder icons
                val ingredients = listOf("Ingredient 1", "Ingredient 2") // Placeholder ingredients
                val instructions = "Cooking instructions here." // Placeholder instructions
                if (description != null && name != null) {
                    Recipe(imageUrl, name, description, icons, ingredients, instructions)
                } else null
            }
            recipes.value = fetchedRecipes
        }
    }

    Column {
        Row() {
            IconButton(onClick = {
                navController.navigate(route = screen.menue.route)
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(340.dp))
            Icon(painter = painterResource(id = R.drawable.airbus_logo_2001), contentDescription = "null", modifier = Modifier.size(30.dp))
        }
        Divider()
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
                navController.navigate(route = screen.myFood.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Add food")
        }

        Text(text = "Recommended:", Modifier.padding(start = 5.dp))

        LazyColumn() {
            items(recipes.value) { recipe ->
                RecipePreviewCard(
                    imageUrl = recipe.imageUrl,
                    title = recipe.title,
                    description = recipe.description,
                    icons = recipe.icons,
                    ingredients = recipe.ingredients
                )
            }
        }

        // Additional content can be added here as needed
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLandPage() {
    LandPage(navController = rememberNavController())
}
