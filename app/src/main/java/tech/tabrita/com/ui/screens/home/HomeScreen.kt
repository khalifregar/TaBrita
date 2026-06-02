package tech.tabrita.com.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.stringResource
import tech.tabrita.com.ui.theme.TaBritaColors
import tech.tabrita.com.ui.theme.TaBritaDimens
import tech.tabrita.com.R
import coil3.compose.AsyncImage
import tech.tabrita.com.domain.model.Category
import tech.tabrita.com.ui.components.ArticleCard
import tech.tabrita.com.ui.components.CategoryChip
import tech.tabrita.com.ui.components.ShimmerArticleCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onArticleClick: (String) -> Unit,
    onNavigateToExplore: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val refreshState = rememberPullToRefreshState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "TaBrita",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = TaBritaDimens.paddingSmall)
                            .size(TaBritaDimens.smallAvatarSize)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .clickable { /* Profile quick action */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(R.string.home_profile),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = { viewModel.refresh() },
            state = refreshState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = TaBritaDimens.paddingLarge)
            ) {
                // Greeting
                item {
                    Column(modifier = Modifier.padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingXSmall)) {
                        Text(
                            text = stringResource(R.string.home_greeting_morning),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stringResource(R.string.home_reader_title),
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }

                // Search bar shortcut
                item {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingMedium)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(TaBritaDimens.cornerLarge))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .clickable { onNavigateToExplore() }
                            .padding(TaBritaDimens.paddingMedium)
                    ) {
                        Text(
                            text = stringResource(R.string.home_search_hint),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Featured / Hero section
                if (state.featuredArticles.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(R.string.home_editor_picks),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingXSmall)
                        )
                    }

                    item {
                        val pagerState = rememberPagerState(pageCount = { state.featuredArticles.size })

                        androidx.compose.foundation.layout.BoxWithConstraints {
                            // Adaptive height: larger on bigger screens, clamped for phones
                            val heroHeight = (maxHeight * 0.42f).coerceIn(TaBritaDimens.heroImageHeightMin, TaBritaDimens.heroImageHeightMax)
                            HorizontalPager(
                                state = pagerState,
                                contentPadding = PaddingValues(horizontal = TaBritaDimens.paddingLarge),
                                pageSpacing = TaBritaDimens.paddingSmall,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(heroHeight)
                            ) { page ->
                                val article = state.featuredArticles[page]
                                FeaturedHeroCard(
                                    article = article,
                                    onClick = { onArticleClick(article.id) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                // Categories
                item {
                    Text(
                        text = stringResource(R.string.home_categories),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(start = TaBritaDimens.paddingLarge, end = TaBritaDimens.paddingLarge, bottom = TaBritaDimens.paddingXSmall)
                    )

                    val categories = Category.entries.toList()
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = TaBritaDimens.paddingLarge),
                        horizontalArrangement = Arrangement.spacedBy(TaBritaDimens.paddingXSmall)
                    ) {
                        items(categories) { category ->
                            CategoryChip(
                                category = category,
                                isSelected = category == state.selectedCategory,
                                onClick = { viewModel.selectCategory(category) }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // For You / Main Feed
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = TaBritaDimens.paddingLarge),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (state.selectedCategory == Category.ALL) "Untuk Anda" else state.selectedCategory.displayName,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }
                    Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))
                }

                if (state.isLoading) {
                    items(4) {
                        ShimmerArticleCard(
                            modifier = Modifier
                                .padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingXSmall)
                        )
                    }
                } else {
                    items(state.forYouArticles, key = { it.id }) { article ->
                        ArticleCard(
                            article = article,
                            onClick = { onArticleClick(article.id) },
                            onBookmarkClick = { viewModel.toggleBookmark(it) },
                            isBookmarked = article.id in state.bookmarkedIds,
                            modifier = Modifier
                                .padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingXSmall)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FeaturedHeroCard(
    article: tech.tabrita.com.domain.model.Article,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(TaBritaDimens.cornerXLarge))
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = article.thumbnailUrl,
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            TaBritaColors.OverlayDark,
                            TaBritaColors.OverlayDarkest
                        ),
                        startY = 40f
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(TaBritaDimens.paddingLarge)
        ) {
            CategoryChip(category = article.category)

            Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))

            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = TaBritaColors.TextOnDarkPrimary,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))

            Text(
                text = "${article.source} • ${article.timeAgo}",
                style = MaterialTheme.typography.labelLarge,
                color = TaBritaColors.TextOnDarkSecondary
            )
        }
    }
}

