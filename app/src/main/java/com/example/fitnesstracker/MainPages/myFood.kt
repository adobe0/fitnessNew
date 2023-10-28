package com.example.fitnesstracker.MainPages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.screen
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFoodPage(navController: NavController) {
    var ingredientText by remember { mutableStateOf("") }
    var ingredientsList by remember { mutableStateOf(listOf<String>()) }
    val database = Firebase.database


    val ingredientsRef = database.getReference("users/aadit/ingredients")

    // Fetch data from Firebase and listen for changes
    ingredientsRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val updatedList = dataSnapshot.children.mapNotNull { it.getValue(String::class.java) }
            ingredientsList = updatedList
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle error if needed
        }
    })

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
                text = "My Food",
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
        //TextField(value = currUser, onValueChange = {currUser = it})

        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(ingredientsList) { index, ingredient ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, CircleShape)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = ingredient, modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        val ingredientToDelete = ingredientsList[index]
                        ingredientsRef.orderByValue().equalTo(ingredientToDelete).addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                dataSnapshot.children.firstOrNull()?.key?.let { key ->
                                    ingredientsRef.child(key).removeValue()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                // Handle error if needed
                            }
                        })
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
                    ingredientsRef.push().setValue(ingredientText)
                    ingredientText = ""
                }
            )
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun showMyFood() {
    MyFoodPage(navController = rememberNavController())
}
