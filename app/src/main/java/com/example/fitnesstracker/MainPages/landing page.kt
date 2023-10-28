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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.R
import com.example.fitnesstracker.assets.RecipePreviewCard
import com.example.fitnesstracker.screen

data class Recipe(
    val imageUrl: Int,
    val title: String,
    val description: String,
    val icons: List<Int>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandPage(navController: NavController) {
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
                navController.navigate(route = screen.explore.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Add food")
        }

        Text(text = "Reccomended:", Modifier.padding(start = 5.dp))

        LazyRow {
            // Sample list of recipes for demonstration purposes
            val recipes = listOf(
                Recipe(
                    imageUrl = R.drawable.sample_card,
                    title = "Delicious Pasta",
                    description = "...",
                    icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298),
                ),
                Recipe(
                    imageUrl = R.drawable.sample_card,
                    title = "Tasty Soup",
                    description = "...",
                    icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298)
                ),
                Recipe(
                    imageUrl = R.drawable.sample_card,
                    title = "Healthy Salad",
                    description = "...",
                    icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298)
                ),
                Recipe(
                    imageUrl = R.drawable.sample_card,
                    title = "Yummy Pizza",
                    description = "...",
                    icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298)
                ),
                Recipe(
                    imageUrl = R.drawable.sample_card,
                    title = "Amazing Dessert",
                    description = "...",
                    icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298)
                )
            )

            items(recipes) { recipe ->
                RecipePreviewCard(
                    imageUrl = recipe.imageUrl,
                    title = recipe.title,
                    description = recipe.description,
                    icons = recipe.icons,
                    onCardClick = {
                    }
                )
            }
        }

        Text(text = "Your Plan", modifier = Modifier.padding(start = 5.dp, top = 15.dp))
        RecipePreviewCard(
            imageUrl = R.drawable.sample_card,
            title = "UnDelicious Pasta",
            description = "...",
            icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298),
            onCardClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLandPage() {
    LandPage(navController = rememberNavController()) // mock NavController for preview
}
