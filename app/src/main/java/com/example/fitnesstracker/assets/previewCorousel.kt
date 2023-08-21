package com.example.fitnesstracker.assets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R

@Composable
fun RecipePreviewCard(
    imageUrl: Int, // Drawable resource ID for the recipe image
    title: String,
    description: String,
    icons: List<Int>, // List of drawable resource IDs for the icons
    onCardClick: () -> Unit // Lambda function to handle card click events
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp)
            .clickable(onClick = onCardClick)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)), // This adds a shadow
        shape = RoundedCornerShape(8.dp),

    ) {
        Column {
            // Recipe image
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
                // Recipe title
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold

                )

                // Icons row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
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

            // Recipe description
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light
            )
        }
    }
}









@Preview(showSystemUi = false)
@Composable
fun RecipeCardSample() {

       /* RecipeDetailScreen(
            imageUrl = R.drawable.sample_card,  // A hypothetical image resource ID
            title = "Mouth-Watering Spaghetti",
            icons = listOf(
                R.drawable.noun_vegan_3029210,  // Hypothetical icons to represent characteristics of the dish
                R.drawable.noun_stopwatch_5062298,
                R.drawable.noun_spicy_4047676
            ),
            description = "An age-old classic, our spaghetti recipe combines rich tomato sauce, fresh basil, and al dente pasta for a simple yet flavorful dish. Drizzled with olive oil and sprinkled with parmesan, it's a treat for all pasta lovers!",
            ingredients = listOf(
                "200g spaghetti",
                "2 cups tomato sauce",
                "1/2 cup fresh basil leaves",
                "1/4 cup olive oil",
                "1/2 cup grated parmesan",
                "2 cloves garlic, minced",
                "Salt and pepper to taste"
            ),
            instructions = "1. Cook the spaghetti in boiling water until al dente.\n" +
                    "2. In a pan, sauté garlic in olive oil until fragrant.\n" +
                    "3. Add the tomato sauce and let it simmer for 10 minutes.\n" +
                    "4. Toss in the cooked spaghetti and mix well.\n" +
                    "5. Serve hot, garnished with fresh basil and parmesan.")*/






    // Sample usage of the RecipePreviewCard
    Column {


        RecipePreviewCard(
            imageUrl = R.drawable.sample_card, // Hypothetical drawable ID for a recipe image
            title = "Delicious Pasta",
            description = "Delve into the rich and creamy flavors of this delightful pasta dish. Prepared with fresh basil, oregano, and a sprinkle of love, our recipe brings forth the authentic taste of Italian cuisine. The sun-dried tomatoes and parmesan shavings not only enhance the taste but also present an aesthetic appeal. Paired best with a glass of white wine.",
            icons = listOf(R.drawable.noun_vegan_3029210,R.drawable.noun_stopwatch_5062298), // Hypothetical drawable IDs for icons
            onCardClick = {
                // Handle the card click event
                // E.g., navigate to the detailed recipe page
                println("Recipe card clicked!")
            }
        )
        RecipePreviewCard(
            imageUrl = R.drawable.sample_card, // Hypothetical drawable ID for a chicken salad image
            title = "Grilled Chicken Salad",
            description = "Savor the light, tangy flavors of this refreshing salad. Tender pieces of grilled chicken rest atop a bed of crispy lettuce, juicy cherry tomatoes, and crunchy croutons. The homemade Caesar dressing drizzled generously completes this culinary delight. It's a healthful choice that doesn't compromise on taste. Perfect for a summer afternoon.",
            icons = listOf(R.drawable.noun_vegan_3029210), // Hypothetical drawable IDs for icons
            onCardClick = {
                // Handle the card click event
                println("Recipe card clicked!")
            }
        )
        RecipePreviewCard(
            imageUrl = R.drawable.sample_card, // Hypothetical drawable ID for a lava cake image
            title = "Chocolate Lava Cake",
            description = "Indulge in the decadent richness of molten chocolate encased in a soft cakey crust. This dessert promises a melt-in-the-mouth experience with every bite. The warm, flowing center is a treat for the senses, making it a favorite for all chocolate lovers. Top it off with a scoop of vanilla ice cream for an irresistible combination.",
            icons = listOf(R.drawable.noun_stopwatch_5062298), // Hypothetical drawable IDs for icons
            onCardClick = {
                // Handle the card click event
                println("Recipe card clicked!")
            }
        )
    }
}
