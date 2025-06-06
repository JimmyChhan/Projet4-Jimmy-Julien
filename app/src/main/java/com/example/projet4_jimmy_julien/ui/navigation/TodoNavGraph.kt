package com.example.projet4_jimmy_julien.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.projet4_jimmy_julien.ui.home.HomeDestination
import com.example.projet4_jimmy_julien.ui.home.HomeScreen
import com.example.projet4_jimmy_julien.ui.todo.TodoEditDestination
import com.example.projet4_jimmy_julien.ui.todo.TodoEditScreen
import com.example.projet4_jimmy_julien.ui.todo.TodoEntryDestination
import com.example.projet4_jimmy_julien.ui.todo.TodoEntryScreen


@Composable
fun TodoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(navigateToTodoEntry = { navController.navigate(TodoEntryDestination.route) },
                navigateToTodoUpdate = {
                    navController.navigate("${TodoEditDestination.route}/${it}")
                })
        }
        composable(route = TodoEntryDestination.route) {
            TodoEntryScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }

        composable(
            route = TodoEditDestination.routeWithArgs,
            arguments = listOf(navArgument(TodoEditDestination.todoIdArg) {
                type = NavType.IntType
            })
        ) {
            TodoEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}
