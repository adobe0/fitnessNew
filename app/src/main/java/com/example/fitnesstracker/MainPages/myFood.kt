package com.example.fitnesstracker.MainPages
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFoodPage() {
    var ingredientText by remember { mutableStateOf("") }
    var ingredientsList by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.noun_hamburger_menu_clicked_4252769),
                contentDescription = "Menu Icon",
                modifier = Modifier.size(24.dp).clickable { }
            )

            Text(
                text = "My Food",
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 110.dp),
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

        // Display the list of submitted texts here
        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(ingredientsList) { index, ingredient ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, CircleShape)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically  // Centering items vertically
                ) {
                    Text(text = ingredient, modifier = Modifier.weight(1f))  // Giving text as much space as possible
                    IconButton(onClick = {
                        ingredientsList = ingredientsList.toMutableList().apply {
                            removeAt(index)
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }

        TextField(
            value = ingredientText,
            onValueChange = { ingredientText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Add ingredient...") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    ingredientsList = ingredientsList + ingredientText // Add the ingredient to the list
                    ingredientText = "" // Clear the TextField
                }
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun showMyFood() {
    MyFoodPage()
}
