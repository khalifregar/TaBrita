package tech.tabrita.com.ui.components

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
import tech.tabrita.com.ui.theme.TaBritaColors
import androidx.compose.ui.unit.dp
import tech.tabrita.com.ui.theme.TaBritaDimens
import tech.tabrita.com.domain.model.Category

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
        TaBritaColors.OverlayMedium
    }

    val textColor = TaBritaColors.TextOnDarkPrimary

    val shape = RoundedCornerShape(50)

    val content = @Composable {
        Text(
            text = "${category.emoji} ${category.displayName}",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
            color = textColor,
            modifier = Modifier.padding(horizontal = TaBritaDimens.paddingSmall, vertical = TaBritaDimens.paddingXSmall)
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

