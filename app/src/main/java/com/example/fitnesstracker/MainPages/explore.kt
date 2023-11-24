package com.example.fitnesstracker.MainPages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.R
import com.example.fitnesstracker.assets.RecipePreviewCard
import com.example.fitnesstracker.screen
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

data class Recipes(
    val imageUrl: Int,
    val title: String,
    val description: String,
    val icons: List<Int>,
    val ingredients: List<String>,
    val instructions: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExplorePage(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val recipes = remember { mutableStateOf<List<Recipes>>(emptyList()) }

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
                    Recipes(imageUrl, name, description, icons, ingredients, instructions)
                } else null
            }
            recipes.value = fetchedRecipes
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                navController.navigate(route = screen.menue.route)
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }

            Text(
                text = "Explore",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 110.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }

        Divider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Button(onClick = { navController.navigate(route = screen.addRecipie.route)}, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Add recipe")
        }

        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            placeholder = { Text("Search for recipes") }
        )

        val filteredRecipes = recipes.value.filter {
            it.title.contains(searchQuery, ignoreCase = true)
        }

        filteredRecipes.forEach { recipe ->
            RecipePreviewCard(
                imageUrl = recipe.imageUrl,
                title = recipe.title,
                description = recipe.description,
                icons = recipe.icons,
                ingredients = recipe.ingredients
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun show() {
    ExplorePage(navController = rememberNavController())
}
