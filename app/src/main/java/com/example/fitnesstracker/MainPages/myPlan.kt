package com.example.fitnesstracker.MainPages
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.fitnesstracker.assets.PlanNugget
import com.example.fitnesstracker.assets.RecipePreviewCard
import com.example.fitnesstracker.screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlanPage(navController: NavController) {
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

        // New Plan Button
        Button(
            onClick = { /* Handle Button Click */ },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, CircleShape)
                .padding(8.dp),
            shape = CircleShape
        ) {
            Text(text = "New Plan", color = Color.White)
        }

        Spacer(modifier = Modifier.size(30.dp))

        // Next Meal Text
        Text(
            text = "Next meal:",
            color = Color.DarkGray,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        RecipePreviewCard(
            imageUrl = R.drawable.orange_fruits,
            title = "food ex",
            description = "food is very yum",
            icons = listOf(R.drawable.noun_stopwatch_5062298)
        ) { }

        Spacer(modifier = Modifier.size(30.dp))

        // Plan Text
        Text(
            text = "Plan:",
            color = Color.DarkGray,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        PlanNugget(title = "food ex2", description = "good")
        PlanNugget(title = "food ex3", description = "bad")

        // Further components for the My Plan page will go here
    }
}

@Preview(showSystemUi = true)
@Composable
fun showMyPlan() {
    MyPlanPage(navController = rememberNavController())
}
