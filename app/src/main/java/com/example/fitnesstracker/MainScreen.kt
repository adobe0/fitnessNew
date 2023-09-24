package com.example.fitnesstracker


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.MainPages.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Scaffold(
        drawerContent = { Drawer(navController = navController) },
        drawerState = drawerState
    ) {
        NavHost(navController = navController, startDestination = MainDestinations.Landing_Page) {
            composable(MainDestinations.Landing_Page) { LandingPage() }
            composable(MainDestinations.explore_Page) { ExplorePage() }
            composable(MainDestinations.myFood_Page) { MyFoodPage() }
            composable(MainDestinations.myPlan_Page) { MyPlanPage() }
        }
    }
}
@Composable
fun Drawer(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Create Drawer items here
        DrawerItem("Landing Page") {
            navController.navigate(MainDestinations.Landing_Page)
        }
        DrawerItem("Explore") {
            navController.navigate(MainDestinations.explore_Page)
        }
        DrawerItem("My Food") {
            navController.navigate(MainDestinations.myFood_Page)
        }
        DrawerItem("My Plan") {
            navController.navigate(MainDestinations.myPlan_Page)
        }
    }
}

@Composable
fun DrawerItem(title: String, onClick: () -> Unit) {
    Text(text = title, modifier = Modifier.clickable(onClick = onClick).padding(16.dp))
}
