package com.tabrita.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tabrita.ui.screens.bookmarks.BookmarksScreen
import com.tabrita.ui.screens.detail.ArticleDetailScreen
import com.tabrita.ui.screens.explore.ExploreScreen
import com.tabrita.ui.screens.home.HomeScreen
import com.tabrita.ui.screens.profile.ProfileScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onArticleClick = { articleId ->
                    navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                },
                onNavigateToExplore = {
                    navController.navigate(Screen.Explore.route) {
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screen.Explore.route) {
            ExploreScreen(
                onArticleClick = { articleId ->
                    navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                }
            )
        }

        composable(Screen.Bookmarks.route) {
            BookmarksScreen(
                onArticleClick = { articleId ->
                    navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }

        composable(
            route = Screen.ArticleDetail.route,
            arguments = listOf(
                androidx.navigation.navArgument("articleId") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId") ?: ""
            ArticleDetailScreen(
                articleId = articleId,
                onNavigateBack = { navController.popBackStack() },
                onRelatedArticleClick = { relatedId ->
                    navController.navigate(Screen.ArticleDetail.createRoute(relatedId)) {
                        // Avoid deep stack
                        popUpTo(Screen.ArticleDetail.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
