package tech.tabrita.com.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import tech.tabrita.com.ui.theme.TaBritaDimens

@Composable
fun ShimmerArticleCard(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerAnim"
    )

    val shimmerColors = listOf(
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim - 200f, 0f),
        end = Offset(translateAnim, 0f)
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(TaBritaDimens.cornerXLarge),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(brush)
            )

            Column(modifier = Modifier.padding(TaBritaDimens.paddingMedium)) {
                // Title lines
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(TaBritaDimens.paddingTiny))
                        .background(brush)
                )
                Spacer(modifier = Modifier.height(TaBritaDimens.paddingXSmall))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(TaBritaDimens.paddingTiny))
                        .background(brush)
                )

                Spacer(modifier = Modifier.height(TaBritaDimens.paddingSmall))

                // Description
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .clip(RoundedCornerShape(TaBritaDimens.paddingTiny))
                        .background(brush)
                )
                Spacer(modifier = Modifier.height(TaBritaDimens.paddingTiny))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(14.dp)
                        .clip(RoundedCornerShape(TaBritaDimens.paddingTiny))
                        .background(brush)
                )

                Spacer(modifier = Modifier.height(TaBritaDimens.paddingSmall))

                Row {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(12.dp)
                            .clip(RoundedCornerShape(TaBritaDimens.paddingTiny))
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.width(TaBritaDimens.paddingXSmall))
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(12.dp)
                            .clip(RoundedCornerShape(TaBritaDimens.paddingTiny))
                            .background(brush)
                    )
                }
            }
        }
    }
}

