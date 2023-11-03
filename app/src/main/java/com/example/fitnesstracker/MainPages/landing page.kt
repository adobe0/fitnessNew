package com.example.fitnesstracker.MainPages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandPage(navController: NavController) {
    val recipes = remember { mutableStateOf<List<Recipe>>(emptyList()) }

    // Fetch recipes from Firebase on launch
    LaunchedEffect(Unit) {
        val database = Firebase.database.reference
        database.child("recipe").get().addOnSuccessListener { dataSnapshot ->
            val fetchedRecipes = dataSnapshot.children.mapNotNull { snapshot ->
                val description = snapshot.child("description").getValue(String::class.java)
                val name = snapshot.child("name").getValue(String::class.java)
                val imageUrl = R.drawable.sample_card // Placeholder image
                val icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298) // Placeholder icons
                if (description != null && name != null) {
                    Recipe(imageUrl, name, description, icons)
                } else null
            }
            recipes.value = fetchedRecipes.shuffled().take(5) // Randomly select 5 recipes
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
            Icon(painter = painterResource(id = R.drawable.airbus_logo_2001), contentDescription ="null", modifier = Modifier.size(30.dp))
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

        LazyRow {
            items(recipes.value) { recipe ->
                RecipePreviewCard(
                    imageUrl = recipe.imageUrl,
                    title = recipe.title,
                    description = recipe.description,
                    icons = recipe.icons,
                    onCardClick = {},
                    ingridients = listOf("h", "hello00")
                )
            }
        }

        Text(text = "Your Plan", modifier = Modifier.padding(start = 5.dp, top = 15.dp))
        RecipePreviewCard(
            imageUrl = R.drawable.sample_card,
            title = "UnDelicious Pasta",
            description = "...",
            icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298),
            onCardClick = {},
            ingridients = listOf("hi", "hello")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLandPage() {
    LandPage(navController = rememberNavController()) // mock NavController for preview
}

