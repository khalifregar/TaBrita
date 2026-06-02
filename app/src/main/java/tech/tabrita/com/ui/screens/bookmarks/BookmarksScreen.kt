package tech.tabrita.com.ui.screens.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.stringResource
import tech.tabrita.com.ui.theme.TaBritaDimens
import tech.tabrita.com.R
import tech.tabrita.com.ui.components.ArticleCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    onArticleClick: (String) -> Unit,
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass? = null,
    viewModel: BookmarksViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    val widthSizeClass = windowSizeClass?.widthSizeClass ?: WindowWidthSizeClass.Compact
    val numColumns = when (widthSizeClass) {
        WindowWidthSizeClass.Expanded -> 3
        WindowWidthSizeClass.Medium -> 2
        else -> 1
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.nav_bookmarks),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        if (state.bookmarkedArticles.isEmpty()) {
            EmptyBookmarks(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(numColumns),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .widthIn(max = TaBritaDimens.maxContentWidth),
                contentPadding = PaddingValues(bottom = TaBritaDimens.paddingLarge)
            ) {
                items(state.bookmarkedArticles, key = { it.id }) { article ->
                    ArticleCard(
                        article = article,
                        onClick = { onArticleClick(article.id) },
                        onBookmarkClick = { viewModel.toggleBookmark(it) },
                        isBookmarked = true,
                        modifier = Modifier.padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingXSmall)
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyBookmarks(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(TaBritaDimens.paddingXXLarge),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "📖", style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(TaBritaDimens.paddingLarge))
        Text(
            text = stringResource(R.string.bookmarks_empty_title),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))
        Text(
            text = stringResource(R.string.bookmarks_empty_desc),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

