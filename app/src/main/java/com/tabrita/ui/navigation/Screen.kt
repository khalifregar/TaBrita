package com.tabrita.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Explore : Screen("explore")
    data object Bookmarks : Screen("bookmarks")
    data object Profile : Screen("profile")
    data object Upload : Screen("upload") // Admin only
    data object ArticleDetail : Screen("article/{articleId}") {
        fun createRoute(articleId: String) = "article/$articleId"
    }
}

val bottomNavScreens = listOf(
    Screen.Home,
    Screen.Explore,
    Screen.Bookmarks,
    Screen.Profile
)
