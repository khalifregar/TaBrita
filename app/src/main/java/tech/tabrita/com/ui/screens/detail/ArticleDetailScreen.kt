package tech.tabrita.com.ui.screens.detail

import android.content.Intent
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.stringResource
import tech.tabrita.com.ui.theme.TaBritaColors
import tech.tabrita.com.ui.theme.TaBritaDimens
import tech.tabrita.com.R
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridItemSpan
import coil3.compose.AsyncImage
import tech.tabrita.com.ui.components.CategoryChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    articleId: String,
    onNavigateBack: () -> Unit,
    onRelatedArticleClick: (String) -> Unit,
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass? = null,
    viewModel: ArticleDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val widthSizeClass = windowSizeClass?.widthSizeClass ?: WindowWidthSizeClass.Compact
    val numColumns = when (widthSizeClass) {
        WindowWidthSizeClass.Expanded -> 3
        WindowWidthSizeClass.Medium -> 2
        else -> 1
    }

    LaunchedEffect(articleId) {
        viewModel.loadArticle(articleId)
    }

    val article = state.article

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.detail_back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    if (article != null) {
                        IconButton(onClick = { viewModel.toggleBookmark() }) {
                            Icon(
                                imageVector = if (state.isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                contentDescription = stringResource(R.string.detail_bookmark),
                                tint = if (state.isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                        val shareArticleText = stringResource(R.string.detail_share_article)
                        val shareBody = stringResource(R.string.detail_share_body, article.title, article.description)
                        IconButton(onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, article.title)
                                putExtra(Intent.EXTRA_TEXT, shareBody)
                            }
                            context.startActivity(Intent.createChooser(shareIntent, shareArticleText))
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = stringResource(R.string.detail_share)
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        if (state.isLoading || article == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
            return@Scaffold
        }

        // Advanced responsive: 2-pane master-detail for Expanded screens (tablets, desktop-like, large landscape)
        // Phone/compact stays beautiful stacked immersive single column. Content capped for readability.
        val isExpanded = widthSizeClass == WindowWidthSizeClass.Expanded

        if (isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Left: primary article reading pane (scrollable, max width for comfort + centered on ultra wide)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(end = TaBritaDimens.paddingMedium)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .widthIn(max = TaBritaDimens.maxContentWidth)
                ) {
                    DetailHeroAndBody(article = article)
                }

                // Right: related pane (persistent sidebar ala modern news apps)
                if (state.relatedArticles.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .width(300.dp)
                            .verticalScroll(rememberScrollState())
                            .padding(start = TaBritaDimens.paddingSmall)
                    ) {
                        Text(
                            text = stringResource(R.string.detail_related),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.padding(bottom = TaBritaDimens.paddingSmall, top = TaBritaDimens.paddingXSmall)
                        )
                        state.relatedArticles.forEach { related ->
                            RelatedArticleCard(
                                article = related,
                                onClick = { onRelatedArticleClick(related.id) },
                                useFullWidth = true
                            )
                            Spacer(Modifier.height(TaBritaDimens.paddingSmall))
                        }
                        Spacer(Modifier.height(TaBritaDimens.paddingXXLarge))
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                DetailHeroAndBody(article = article)

                // Related articles section (row or grid based on size for compact/medium)
                if (state.relatedArticles.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))

                    Text(
                        text = stringResource(R.string.detail_related),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier
                            .padding(horizontal = TaBritaDimens.paddingLarge)
                            .padding(bottom = TaBritaDimens.paddingSmall)
                    )

                    if (numColumns > 1) {
                        // Advanced grid for related on larger screens
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(kotlin.math.min(numColumns, 3)),
                            contentPadding = PaddingValues(horizontal = TaBritaDimens.paddingLarge),
                            verticalArrangement = Arrangement.spacedBy(TaBritaDimens.paddingSmall),
                            horizontalArrangement = Arrangement.spacedBy(TaBritaDimens.relatedCardSpacing),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 400.dp)
                                .padding(bottom = TaBritaDimens.paddingXXLarge)
                        ) {
                            items(state.relatedArticles) { related ->
                                RelatedArticleCard(
                                    article = related,
                                    onClick = { onRelatedArticleClick(related.id) },
                                    cardWidth = TaBritaDimens.relatedCardWidthDefault
                                )
                            }
                        }
                    } else {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = TaBritaDimens.paddingLarge),
                            horizontalArrangement = Arrangement.spacedBy(TaBritaDimens.relatedCardSpacing)
                        ) {
                            items(state.relatedArticles) { related ->
                                RelatedArticleCard(
                                    article = related,
                                    onClick = { onRelatedArticleClick(related.id) },
                                    cardWidth = TaBritaDimens.relatedCardWidthDefault
                                )
                            }
                        }
                    }
                }
            }
        }

        // Precompute share strings during composition (cannot call stringResource inside onClick lambdas)
        val fabShareText = stringResource(R.string.action_share)
        val fabShareBody = stringResource(R.string.detail_share_fab_body, article.title)

        // Floating action share + bookmark (modern touch)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(TaBritaDimens.paddingLarge),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(TaBritaDimens.fabSpacing)) {
                FloatingActionButton(
                    onClick = { viewModel.toggleBookmark() },
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = if (state.isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                    elevation = FloatingActionButtonDefaults.elevation(TaBritaDimens.elevationMedium)
                ) {
                    Icon(
                        imageVector = if (state.isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = stringResource(R.string.action_bookmark)
                    )
                }

                FloatingActionButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, article.title)
                            putExtra(Intent.EXTRA_TEXT, fabShareBody)
                        }
                        context.startActivity(Intent.createChooser(shareIntent, fabShareText))
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(Icons.Filled.Share, contentDescription = stringResource(R.string.action_share))
                }
            }
        }
    }
}

/**
 * Reusable article hero + meta + rich body (slices/images) for both stacked (phone) and 2-pane (expanded) layouts.
 * Hero height uses constraints for advanced fluid responsive on large viewports.
 */
@Composable
private fun DetailHeroAndBody(article: tech.tabrita.com.domain.model.Article, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        // Hero - adaptive height for non-rigid feel
        androidx.compose.foundation.layout.BoxWithConstraints {
            val heroHeight = (maxHeight * 0.38f).coerceIn(TaBritaDimens.heroImageHeightMin, TaBritaDimens.heroImageHeightMax)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heroHeight)
            ) {
                AsyncImage(
                    model = article.thumbnailUrl,
                    contentDescription = article.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Gradient for title overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    TaBritaColors.OverlayLight,
                                    TaBritaColors.OverlayDarkest
                                ),
                                startY = 40f
                            )
                        )
                )

                // Category + Title overlay
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(TaBritaDimens.paddingLarge)
                ) {
                    CategoryChip(category = article.category)
                    Spacer(modifier = Modifier.height(TaBritaDimens.paddingMedium))
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = TaBritaColors.TextOnDarkPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        // Meta info
        Column(modifier = Modifier.padding(horizontal = TaBritaDimens.paddingLarge, vertical = TaBritaDimens.paddingLarge)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(text = "  ${stringResource(R.string.detail_bullet)}  ", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(
                    text = article.author,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.timeAgo,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(text = "  ${stringResource(R.string.detail_bullet)}  ", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(
                    text = stringResource(R.string.detail_read_time_inline, article.readTimeMinutes),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Article body - beautiful readable typography with slices/images (modern news style)
        Column(
            modifier = Modifier
                .padding(horizontal = TaBritaDimens.paddingLarge)
                .padding(bottom = TaBritaDimens.paddingLarge)
        ) {
            article.contentBlocks.forEach { block ->
                Text(
                    text = block.text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.15f
                )
                block.imageUrl?.let { imgUrl ->
                    Spacer(modifier = Modifier.height(TaBritaDimens.paddingMedium))
                    AsyncImage(
                        model = imgUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(TaBritaDimens.cardImageHeightLarge)
                            .clip(RoundedCornerShape(TaBritaDimens.cornerMedium))
                    )
                    Spacer(modifier = Modifier.height(TaBritaDimens.paddingMedium))
                }
            }
        }
    }
}

@Composable
private fun RelatedArticleCard(
    article: tech.tabrita.com.domain.model.Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    useFullWidth: Boolean = false,
    cardWidth: androidx.compose.ui.unit.Dp = TaBritaDimens.relatedCardWidthDefault
) {
    val cardMod = if (useFullWidth) modifier.fillMaxWidth() else modifier.width(cardWidth)
    Column(
        modifier = cardMod
            .clip(RoundedCornerShape(TaBritaDimens.cornerLarge))
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = article.thumbnailUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(TaBritaDimens.cardImageHeightSmall)
                .clip(RoundedCornerShape(topStart = TaBritaDimens.cornerLarge, topEnd = TaBritaDimens.cornerLarge))
        )

        Column(modifier = Modifier.padding(TaBritaDimens.paddingSmall)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))
            Text(
                text = "${article.source} • ${article.timeAgo}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

