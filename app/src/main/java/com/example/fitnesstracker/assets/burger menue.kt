/*package com.example.fitnesstracker.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.assets.PlanNugget
import com.example.fitnesstracker.assets.RecipePreviewCard


@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    val openDrawer = rememberUpdatedState(false)
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            // Drawer content
            Drawer(
                onDestinationClicked = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                    scaffoldState.drawerState.close()
                }
            )
        }
    ) {
        NavHost(navController = navController, startDestination = MainDestinations.NEW_USER_ROUTE) {
            composable(MainDestinations.NEW_USER_ROUTE) { NewUserPage() }
            composable(MainDestinations.LOGIN_ROUTE) { LoginPage() }
            // ... other composable destinations
        }
    }
}

@Composable
fun Drawer(onDestinationClicked: (String) -> Unit) {
    Column {
        DrawerButton("New User", MainDestinations.NEW_USER_ROUTE, onDestinationClicked)
        DrawerButton("Login", MainDestinations.LOGIN_ROUTE, onDestinationClicked)
        // ... other DrawerButtons
    }
}

@Composable
fun DrawerButton(
    text: String,
    route: String,
    onDestinationClicked: (String) -> Unit
) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDestinationClicked(route) }
            .padding(16.dp)
    )
}
*/
