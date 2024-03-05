package com.example.fitnesstracker

import AddRecipe
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.MainPages.ExplorePage
import com.example.fitnesstracker.MainPages.LandPage
import com.example.fitnesstracker.MainPages.MyFoodPage
import com.example.fitnesstracker.MainPages.MyPlanPage
import com.example.fitnesstracker.assets.menuSwitching
import com.example.fitnesstracker.logInUserVarification.EmailPage
import com.example.fitnesstracker.logInUserVarification.ForgotPasswordSecurity
import com.example.fitnesstracker.logInUserVarification.PasswordReset
import com.example.fitnesstracker.logInUserVarification.SecurityQuestionPage
import com.example.fitnesstracker.logInUserVarification.SignUpPage

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = screen.login.route
    ){
        composable(// providing the file routing for each used page
            route = screen.landingPage.route){
            LandPage(navController = navController)
        }
        composable(
            route = screen.explore.route){
            ExplorePage(navController = navController)
        }
        composable(
            route = screen.myFood.route
        ){ MyFoodPage(navController = navController)}
        composable(
        route = screen.myPlan.route){
            MyPlanPage(navController = navController)
        }
        composable(
            route = screen.login.route){
            EmailPage(navController = navController)
        }
        composable(
            route = screen.forgotPass1.route){
            ForgotPasswordSecurity(rememberNavController())// inserting nav controller in each page
        }
        composable(
            route = screen.forgotpass2.route){
            PasswordReset(rememberNavController())
        }

        composable(
            route = screen.newUser.route){
            SignUpPage()
        }
        composable(
            route = screen.NUSecQ.route){
            SecurityQuestionPage()
        }
        composable(
            route = screen.menue.route){
            menuSwitching(navController)
        }
        composable(
            route = screen.addRecipie.route){
            AddRecipe(navController)
        }


    }
}
