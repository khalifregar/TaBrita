package tech.tabrita.com.ui.screens.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.ui.res.stringResource
import tech.tabrita.com.ui.theme.TaBritaDimens
import tech.tabrita.com.R
import tech.tabrita.com.domain.model.Category
import tech.tabrita.com.ui.components.ArticleCard
import tech.tabrita.com.ui.components.CategoryChip
import tech.tabrita.com.ui.components.ShimmerArticleCard
import tech.tabrita.com.ui.components.TaBritaSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    onArticleClick: (String) -> Unit,
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass? = null,
    viewModel: ExploreViewModel = hiltViewModel()
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
                        text = stringResource(R.string.explore_title),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Search
            TaBritaSearchBar(
                query = state.searchQuery,
                onQueryChange = viewModel::onSearchQueryChange,
                placeholder = stringResource(R.string.explore_search_placeholder),
                modifier = Modifier.padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingMedium)
            )

            // Category filters
            val categories = Category.entries.toList()
            LazyRow(
                contentPadding = PaddingValues(horizontal = TaBritaDimens.paddingLarge),
                horizontalArrangement = Arrangement.spacedBy(TaBritaDimens.paddingXSmall),
                modifier = Modifier.padding(bottom = TaBritaDimens.paddingXSmall)
            ) {
                items(categories) { cat ->
                    CategoryChip(
                        category = cat,
                        isSelected = cat == state.selectedCategory,
                        onClick = { viewModel.selectCategory(cat) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))

            if (state.isLoading) {
                LazyColumn {
                    items(5) {
                        ShimmerArticleCard(
                            modifier = Modifier.padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingXSmall)
                        )
                    }
                }
            } else if (state.filteredArticles.isEmpty()) {
                EmptySearchResult(query = state.searchQuery)
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(numColumns),
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentWidth(androidx.compose.ui.Alignment.CenterHorizontally)
                        .widthIn(max = TaBritaDimens.maxContentWidth),
                    contentPadding = PaddingValues(bottom = TaBritaDimens.paddingContentBottom)
                ) {
                    items(state.filteredArticles, key = { it.id }) { article ->
                        ArticleCard(
                            article = article,
                            onClick = { onArticleClick(article.id) },
                            onBookmarkClick = { viewModel.toggleBookmark(it) },
                            isBookmarked = article.id in state.bookmarkedIds,
                            modifier = Modifier.padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingXSmall)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptySearchResult(query: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(TaBritaDimens.paddingXXLarge),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(
            text = "😕",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(TaBritaDimens.paddingMedium))
        Text(
            text = if (query.isNotBlank())
                stringResource(R.string.empty_search, query)
            else stringResource(R.string.explore_empty),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))
        Text(
            text = stringResource(R.string.explore_empty_desc),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

