package com.example.expensetrackerapp

sealed class Screen(val route : String) {
    data object HomeView : Screen("home_view")
    data object ListView : Screen("list_view")

}