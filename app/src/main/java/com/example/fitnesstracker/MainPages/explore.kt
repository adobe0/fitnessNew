package com.example.fitnesstracker.MainPages
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

        var textFieldValue1 by remember { mutableStateOf("") }
        var textFieldValue2 by remember { mutableStateOf("") }

        TextField(
            value = textFieldValue1,
            onValueChange = { textFieldValue1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            TextField(
                value = textFieldValue2,
                onValueChange = { textFieldValue2 = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            )

            Button(onClick = { /* Handle button click here */ }) {
                Text("Add Recipe")
            }
        }

        RecipePreviewCard(
            imageUrl = R.drawable.orange_fruits,
            title = "Recipe Title 1",
            description = "This is a description for Recipe 1.",
            icons = listOf(R.drawable.noun_vegan_3029210, R.drawable.noun_stopwatch_5062298),
            onCardClick = { }
        )

        Spacer(modifier = Modifier.height(8.dp))

        RecipePreviewCard(
            imageUrl = R.drawable.sample_card,
            title = "Recipe Title 2",
            description = "This is a description for Recipe 2.",
            icons = listOf(R.drawable.noun_spicy_4047676, R.drawable.noun_vegan_3029210),
            onCardClick = { }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun show(){
    ExplorePage(navController = rememberNavController())
}