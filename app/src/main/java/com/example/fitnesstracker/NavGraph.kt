package com.example.fitnesstracker

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.MainPages.ExplorePage
import com.example.fitnesstracker.screen.*
import com.example.fitnesstracker.MainPages.LandPage

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = screen.landingPage.route
    ){
        composable(
            route = screen.landingPage.route){
            LandPage(navController = rememberNavController())
        }
        composable(
            route = screen.explore.route){
            ExplorePage()
        }
    }
}