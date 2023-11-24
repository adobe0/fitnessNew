package com.example.fitnesstracker.MainPages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.fitnesstracker.R
import com.example.fitnesstracker.assets.RecipePreviewCard
import com.example.fitnesstracker.assets.addPlanCard
import com.example.fitnesstracker.screen
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// Rename the Recipe data class to MealRecipe
data class MealRecipe(
    val name: String,
    val description: String,
    val ingredients: List<String>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlanPage(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    val recipes = remember { mutableStateOf<List<MealRecipe>>(emptyList()) }
    val plannedMeals = remember { mutableStateOf<List<MealRecipe>>(emptyList()) }
    val IngReq = remember { mutableStateOf<List<String>>(emptyList()) }

    // Function to fetch recipes from Firebase
    fun fetchIngredientsForSelectedRecipes() {
        IngReq.value = plannedMeals.value.flatMap { it.ingredients }.distinct()
    }
    fun fetchPlannedMeals() {
        val database = Firebase.database.reference
        database.child("planned").get().addOnSuccessListener { dataSnapshot ->
            val plannedNames = dataSnapshot.children.mapNotNull { it.getValue(String::class.java) }
            plannedMeals.value = recipes.value.filter { it.name in plannedNames }
            fetchIngredientsForSelectedRecipes()
        }
    }
    fun fetchRecipes() {
        val database = Firebase.database.reference
        database.child("recipes").get().addOnSuccessListener { dataSnapshot ->
            val fetchedRecipes = dataSnapshot.children.mapNotNull { snapshot ->
                val name = snapshot.child("name").getValue(String::class.java)
                val description = snapshot.child("description").getValue(String::class.java)
                val ingredients = snapshot.child("ingredients").getValue(object : GenericTypeIndicator<List<String>>() {})

                if (name != null && description != null && ingredients != null) {
                    MealRecipe(name, description, ingredients)
                } else null
            }
            recipes.value = fetchedRecipes
            fetchPlannedMeals() // Fetch planned meals
        }
    }





    LaunchedEffect(Unit) {
        fetchRecipes()
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
                text = "My Plan",
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

        Button(
            onClick = {
                showDialog = true
                fetchRecipes() // Fetch recipes when dialog is shown
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, CircleShape)
                .padding(8.dp),
            shape = CircleShape
        ) {
            Text(text = "New Plan", color = Color.White)
        }

        Spacer(modifier = Modifier.size(30.dp))

        Text(text = "Ingredients required:")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F0F0))
                .padding(8.dp)
        ) {
            LazyColumn {
                items(IngReq.value) { ingredient ->
                    Text(
                        text = ingredient,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(30.dp))

        if (plannedMeals.value.isNotEmpty()) {
            Text(
                text = "Next meal:",
                color = Color.DarkGray,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.Start)
            )

            LazyRow {
                items(plannedMeals.value) { recipe ->
                    RecipePreviewCard(
                        imageUrl = R.drawable.orange_fruits, // Replace with actual image URL
                        title = recipe.name,
                        description = recipe.description,
                        icons = listOf(R.drawable.noun_stopwatch_5062298), // Replace with actual icons
                        ingredients = recipe.ingredients
                    )
                }
            }
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Select Recipes",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF008080),
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                var searchText by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Search Recipes") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                val filteredRecipes = recipes.value.filter {
                    it.name.contains(searchText, ignoreCase = true)
                }

                LazyColumn {
                    items(filteredRecipes) { recipe ->
                        addPlanCard(recipeName = recipe.name, recipeDescription = recipe.description)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
