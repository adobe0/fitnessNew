
package com.example.fitnesstracker.logInUserVarification


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.screen

@Composable
fun menue(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Landing Page",
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navController.navigate(screen.landingPage.route)
                },
            fontSize = 20.sp
        )
        Text(
            text = "Explore Page",
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navController.navigate(screen.explore.route)
                },
            fontSize = 20.sp
        )

    }
}


@Preview(showSystemUi = true)
@Composable
fun showMe(){
    menue(navController = rememberNavController())
}
