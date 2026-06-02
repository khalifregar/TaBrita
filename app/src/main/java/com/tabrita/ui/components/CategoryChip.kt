package com.tabrita.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tabrita.domain.model.Category

@Composable
fun CategoryChip(
    category: Category,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val bgColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.Black.copy(alpha = 0.45f)
    }

    val textColor = Color.White

    val shape = RoundedCornerShape(50)

    val content = @Composable {
        Text(
            text = "${category.emoji} ${category.displayName}",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
            color = textColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }

    val chipModifier = modifier
        .clip(shape)
        .background(bgColor)
        .then(
            if (onClick != null) {
                Modifier.clickable(onClick = onClick)
            } else Modifier
        )

    Box(modifier = chipModifier) {
        content()
    }
}
