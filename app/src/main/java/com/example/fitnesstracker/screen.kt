package com.example.fitnesstracker

sealed class  screen(val route: String) {
    object landingPage: screen("Landing_Page")
    object explore: screen(route = "explore_Page")
    object myFood: screen(route = "myFood_Page")
    object myPlan:screen(route ="myPlan_Page")
    object login: screen(route ="login_Page")
    object forgotPass1: screen(route ="FP1_Page")
    object forgotpass2: screen(route ="FP2_Page")
    object newUser: screen(route ="NewUser_Page")
    object NUSecQ: screen(route ="NUSecQ_Page")
    object menue: screen(route = "switcher")
    object addRecipie: screen(route = "Addrecipe")
}