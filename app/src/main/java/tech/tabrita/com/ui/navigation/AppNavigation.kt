package tech.tabrita.com.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import tech.tabrita.com.ui.screens.bookmarks.BookmarksScreen
import tech.tabrita.com.ui.screens.detail.ArticleDetailScreen
import tech.tabrita.com.ui.screens.explore.ExploreScreen
import tech.tabrita.com.ui.screens.home.HomeScreen
import tech.tabrita.com.ui.screens.profile.ProfileScreen
import tech.tabrita.com.ui.screens.upload.UploadNewsScreen
import tech.tabrita.com.data.auth.AppUser

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    currentUser: AppUser? = null,
    windowSizeClass: WindowSizeClass? = null
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
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
                },
                windowSizeClass = windowSizeClass
            )
        }

        composable(Screen.Explore.route) {
            ExploreScreen(
                onArticleClick = { articleId ->
                    navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                },
                windowSizeClass = windowSizeClass
            )
        }

        composable(Screen.Bookmarks.route) {
            BookmarksScreen(
                onArticleClick = { articleId ->
                    navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                },
                windowSizeClass = windowSizeClass
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(windowSizeClass = windowSizeClass)
        }

        composable(Screen.Upload.route) {
            if (currentUser?.isAdmin == true) {
                UploadNewsScreen(
                    currentUser = currentUser,
                    onUploadSuccess = {
                        // Go back to home after successful upload
                        navController.popBackStack(Screen.Home.route, false)
                    },
                    windowSizeClass = windowSizeClass
                )
            } else {
                // Fallback
                HomeScreen(
                    onArticleClick = { articleId ->
                        navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                    },
                    onNavigateToExplore = { /* no-op */ },
                    windowSizeClass = windowSizeClass
                )
            }
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
                },
                windowSizeClass = windowSizeClass
            )
        }
    }
}

