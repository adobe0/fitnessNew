package com.example.fitnesstracker.assets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R

@Composable
fun RecipeDetailScreen(
    imageUrl: Int,
    title: String,
    icons: List<Int>,
    description: String,
    ingredients: List<String>,
    instructions: String
) {
    // The whole screen will be scrollable
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = imageUrl),
            contentDescription = "Recipe Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        // Title of the dish
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        // Image of the dish

        // Icons row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End  // Push icons to the right
        ) {
            icons.forEach { iconRes ->
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
// Add some space between icons
                )
            }
        }

        // Description of the dish
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(16.dp)
        )

        // Ingredients
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
        ingredients.forEach { ingredient ->
            Text(
                text = "- $ingredient",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp)
            )
        }

        // Cooking Instructions
        Text(
            text = "Instructions",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
        Text(
            text = instructions,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun RecipeDetailPreview() {
    RecipeDetailScreen(
        imageUrl = R.drawable.sample_card,  // Hypothetical drawable ID for a recipe image
        title = "Delicious Pasta",
        icons = listOf(R.drawable.noun_stopwatch_5062298, R.drawable.noun_spicy_4047676),  // Hypothetical drawable IDs for icons
        description = "Delve into the rich and creamy flavors of this delightful pasta dish. Prepared with fresh basil, oregano, and a sprinkle of love, our recipe brings forth the authentic taste of Italian cuisine. The sun-dried tomatoes and parmesan shavings not only enhance the taste but also present an aesthetic appeal. Paired best with a glass of white wine.",
        ingredients = List(20) { "Ingredient ${it + 1}" },  // A long list of ingredients
        instructions = """
            Step 1: Boil the pasta until it's al dente. This usually takes around 8-10 minutes.
            
            Step 2: In a separate pan, sauté garlic and onions until they turn translucent.
            
            Step 3: Add the sun-dried tomatoes, fresh basil, and oregano. Stir for 2-3 minutes.
            
            Step 4: Pour in the tomato sauce and let it simmer for 10 minutes.
            
            Step 5: Add the boiled pasta to the sauce. Mix well to ensure each pasta is coated with the sauce.
            
            Step 6: Serve hot and sprinkle parmesan shavings on top. Enjoy your delicious pasta!
            
            Note: You can add other vegetables or even protein like chicken or shrimp as per your preference.
            
            Bonus Tip: To enhance the flavor, try adding a dash of chili flakes for a spicy kick!
            
            Another Tip: Pair this pasta with a side of garlic bread or a fresh salad to complete the meal.
            
            Storage Instructions: If you have leftovers, store them in an airtight container in the refrigerator. Reheat before consuming. It's best consumed within 2 days.
            
            Nutritional Information: This pasta provides about 350 calories per serving, with a good balance of carbohydrates, protein, and fats. It's also a good source of dietary fiber and essential vitamins.
            
            Additional Notes: You can experiment with different types of pasta like penne, fusilli, or spaghetti. Each type brings a unique texture and taste to the dish.
            
            We hope you enjoy making and eating this delightful pasta dish. Happy cooking!
        """.trimIndent()
    )
}


