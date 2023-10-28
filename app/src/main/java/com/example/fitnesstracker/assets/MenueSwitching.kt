package com.example.fitnesstracker.assets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.screen

@Composable
fun menuSwitching(navController: NavController) {
    var sample: String = " "

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textStyle = TextStyle(fontSize = 50.sp, color = Color(0xFF00897B)) // Teal color for all text items

            Text(
                text = "Home",
                style = textStyle,
                modifier = Modifier.padding(30.dp).clickable {navController.navigate(route = screen.landingPage.route) }
            )
            Text(
                text = "Explore",
                style = textStyle,
                modifier = Modifier.padding(30.dp).clickable { navController.navigate(route = screen.explore.route) }
            )
            Text(
                text = "myFood",
                style = textStyle,
                modifier = Modifier.padding(30.dp).clickable {navController.navigate(route = screen.myFood.route) }
            )
            Text(
                text = "myPlan",
                style = textStyle,
                modifier = Modifier.padding(30.dp).clickable { navController.navigate(route = screen.myPlan.route) }
            )
            Text(
                text = "Settings",
                style = textStyle,
                modifier = Modifier.padding(30.dp).clickable { sample = "inop"}
            )
            Text(
                text = sample,
                style = TextStyle(color = Color.Red)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun show() {
    menuSwitching(navController = rememberNavController())
}
