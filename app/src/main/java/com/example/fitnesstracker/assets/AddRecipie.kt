
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.screen
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddRecipe(navController: NavController) {
    val (recipeName, setRecipeName) = remember { mutableStateOf(TextFieldValue()) }
    val (description, setDescription) = remember { mutableStateOf(TextFieldValue()) }
    var ingredients by remember { mutableStateOf(listOf<String>()) }
    val (ingredient, setIngredient) = remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf(listOf<String>()) }
    val (instruction, setInstruction) = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun insertRecipe() {
        if (recipeName.text.isBlank()) {
            // Handle empty recipe name case, e.g., show an error message
            return
        }
        val recipe = mapOf(
            "name" to recipeName.text,
            "description" to description.text,
            "ingredients" to ingredients,
            "instructions" to instructions
        )
        database.child("recipes").child(recipeName.text).setValue(recipe)
    }

    // Gradient brush for the button
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF008080), // Teal color
            Color(0xFF808000)  // Olive color
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(text = "Recipe Name", modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = recipeName,
                    onValueChange = setRecipeName,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Text(text = "Description", modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = setDescription,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 150.dp, max = 200.dp)
                )
            }

            item {
                Text(text = "Ingredients", modifier = Modifier.padding(bottom = 8.dp))
                ingredients.forEachIndexed { index, currentIngredient ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = currentIngredient, modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete ingredient",
                            modifier = Modifier
                                .clickable { ingredients = ingredients.toMutableList().also { it.removeAt(index) } }
                        )
                    }
                }
                OutlinedTextField(
                    value = ingredient,
                    onValueChange = setIngredient,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            ingredients = ingredients + ingredient
                            setIngredient("")
                            keyboardController?.hide()
                        }
                    ),
                    singleLine = true,
                    placeholder = { Text(text = "Add an ingredient and press enter") }
                )
            }

            item {
                Text(text = "Instructions", modifier = Modifier.padding(bottom = 8.dp))
                instructions.forEachIndexed { index, currentInstruction ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = currentInstruction, modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete instruction",
                            modifier = Modifier
                                .clickable { instructions = instructions.toMutableList().also { it.removeAt(index) } }
                        )
                    }
                }
                OutlinedTextField(
                    value = instruction,
                    onValueChange = setInstruction,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            instructions = instructions + instruction
                            setInstruction("")
                            keyboardController?.hide()
                        }
                    ),
                    singleLine = true,
                    placeholder = { Text(text = "Add an instruction and press enter") }
                )
            }
        }
        Button(
            onClick = {
                insertRecipe()
                navController.navigate(screen.explore.route)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(brush = gradientBrush, shape = RoundedCornerShape(50))
        ) {
            Text(text = "Insert Recipe")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddRecipe() {
    AddRecipe(rememberNavController())
}
