package com.example.fitnesstracker.MainPages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.assets.RecipePreviewCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandPage() {
    Column {
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
                // Define the action to be taken when the button is clicked
                //navigate to another screen or perform some action.
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),


        ) {
            Text(text = "Add food")
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Reccomended:", Modifier.padding(start = 5.dp))
        RecipePreviewCard(
            imageUrl = R.drawable.sample_card, // Hypothetical drawable ID for a recipe image
            title = "Delicious Pasta",
            description = "Delve into the rich and creamy flavors of this delightful pasta dish. Prepared with fresh basil, oregano, and a sprinkle of love, our recipe brings forth the authentic taste of Italian cuisine. The sun-dried tomatoes and parmesan shavings not only enhance the taste but also present an aesthetic appeal. Paired best with a glass of white wine.",
            icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298), // Hypothetical drawable IDs for icons
            onCardClick = {}
        )

    Text(text = "Your Plan", modifier = Modifier.padding(start = 5.dp,top = 15.dp))
        RecipePreviewCard(
            imageUrl = R.drawable.sample_card, // Hypothetical drawable ID for a recipe image
            title = "UnDelicious Pasta",
            description = "Delve into the rich and creamy flavors of this delightful pasta dish. Prepared with fresh basil, oregano, and a sprinkle of love, our recipe brings forth the authentic taste of Italian cuisine. The sun-dried tomatoes and parmesan shavings not only enhance the taste but also present an aesthetic appeal. Paired best with a glass of white wine.",
            icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298), // Hypothetical drawable IDs for icons
            onCardClick = {}
        )

}}

@Preview(showSystemUi = true)
@Composable
fun look(){
    LandPage()
}