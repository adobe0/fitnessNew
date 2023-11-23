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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExplorePage(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

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
            Text(text = "Add recipie")

            
        }

        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            placeholder = { Text("Search for recipes") }
        )

        RecipePreviewCard(
            imageUrl = R.drawable.orange_fruits,
            title = "Tasty Pasta",
            description = "This is an example pasta recipe",
            icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298),
            ingredients = listOf("Pasta", "Tomato Sauce"), // Placeholder ingredients

        )

        Spacer(modifier = Modifier.height(8.dp))

        RecipePreviewCard(
            imageUrl = R.drawable.sample_card,
            title = "Tasty Pizza",
            description = "This is an example pizza recipe",
            icons = listOf(R.drawable.noun_spicy_4047676, R.drawable.noun_vegan_3029210),
            ingredients = listOf("Dough", "Cheese"), // Placeholder ingredients
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun show() {
    ExplorePage(navController = rememberNavController())
}
