package com.example.expensetrackerapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(
    viewModel: ExpenseViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
NavHost(
    navController = navController,
    startDestination = Screen.HomeView.route
){
composable(Screen.HomeView.route){
    HomeView(navController = navController, viewModel = viewModel )
}
    composable(Screen.ListView.route+"/{id}",
        arguments = listOf(
            navArgument("id"){
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            }
        )){
            entry->
            val id = if(entry.arguments!=null) entry.arguments!!.getLong("id") else 0L
            ListView(id = id, viewModel = viewModel, navController = navController)
        }
}
}