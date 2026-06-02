package tech.tabrita.com.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.ui.res.stringResource
import tech.tabrita.com.ui.theme.TaBritaColors
import tech.tabrita.com.ui.theme.TaBritaDimens
import tech.tabrita.com.R
import tech.tabrita.com.domain.model.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit,
    onBookmarkClick: (String) -> Unit,
    isBookmarked: Boolean = false,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        label = "cardScale"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable {
                scope.launch {
                    isPressed = true
                    delay(60)
                    isPressed = false
                    onClick()
                }
            },
        shape = RoundedCornerShape(TaBritaDimens.cornerXLarge),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = TaBritaDimens.elevationSmall)
    ) {
        Column {
            // Hero image with gradient overlay + category badge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                AsyncImage(
                    model = article.thumbnailUrl,
                    contentDescription = article.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(topStart = TaBritaDimens.cornerXLarge, topEnd = TaBritaDimens.cornerXLarge))
                )

                // Beautiful gradient for text readability
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    TaBritaColors.OverlayDarker
                                ),
                                startY = 80f
                            )
                        )
                )

                // Category chip
                CategoryChip(
                    category = article.category,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(TaBritaDimens.paddingSmall)
                )

                // Bookmark button
                IconButton(
                    onClick = { onBookmarkClick(article.id) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(TaBritaDimens.paddingTiny)
                        .size(TaBritaDimens.bookmarkIconSize)
                        .background(
                            color = TaBritaColors.OverlayDark,
                            shape = RoundedCornerShape(TaBritaDimens.cornerMedium)
                        )
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = stringResource(R.string.action_bookmark_alt),
                        tint = if (isBookmarked) MaterialTheme.colorScheme.primary else TaBritaColors.TextOnDarkPrimary,
                        modifier = Modifier.size(TaBritaDimens.iconSizeSmall)
                    )
                }

                // Source + time at bottom of image
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(TaBritaDimens.paddingSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = article.source,
                        style = MaterialTheme.typography.labelMedium,
                        color = TaBritaColors.TextOnDarkSecondary
                    )
                    Spacer(modifier = Modifier.width(TaBritaDimens.smallGap))
                    Text(
                        text = "•",
                        color = TaBritaColors.TextOnDarkTertiary
                    )
                    Spacer(modifier = Modifier.width(TaBritaDimens.smallGap))
                    Text(
                        text = article.timeAgo,
                        style = MaterialTheme.typography.labelMedium,
                        color = TaBritaColors.TextOnDarkSecondary
                    )
                }
            }

            // Content area
            Column(modifier = Modifier.padding(horizontal = TaBritaDimens.paddingMedium, vertical = TaBritaDimens.paddingSmall)) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))

                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(TaBritaDimens.paddingSmall))

                // Meta row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(TaBritaDimens.paddingXSmall)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(TaBritaDimens.paddingXSmall)
                    )
                    Text(
                        text = stringResource(tech.tabrita.com.R.string.detail_read_time, article.readTimeMinutes),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "•",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    )

                    Text(
                        text = article.author,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

