package tech.tabrita.com.ui.theme

import androidx.compose.ui.graphics.Color

// Modern aesthetic news palette - Dark first (default for eye-catching experience)
object TaBritaColors {
    // Primary Brand
    val Accent = Color(0xFFE94560)          // Vibrant eye-catching coral/red
    val AccentLight = Color(0xFFFF6B6B)     // Lighter for hover/pressed

    // Dark Theme (main)
    val Background = Color(0xFF0F172A)      // Deep slate
    val Surface = Color(0xFF1E293B)         // Card / elevated surface
    val SurfaceVariant = Color(0xFF334155)  // Subtle variant
    val OnSurface = Color(0xFFF8FAFC)       // Primary text
    val OnSurfaceVariant = Color(0xFF94A3B8)// Secondary text / captions
    val Outline = Color(0xFF475569)         // Borders / dividers

    // Light Theme (optional but complete)
    val LightBackground = Color(0xFFF8FAFC)
    val LightSurface = Color(0xFFFFFFFF)
    val LightSurfaceVariant = Color(0xFFF1F5F9)
    val LightOnSurface = Color(0xFF0F172A)
    val LightOnSurfaceVariant = Color(0xFF475569)
    val LightOutline = Color(0xFFE2E8F0)

    // Functional
    val Success = Color(0xFF10B981)
    val Warning = Color(0xFFF59E0B)
    val Error = Color(0xFFEF4444)

    // Custom overlays and text on dark surfaces (used in cards, hero, etc.)
    val OverlayDark = Color.Black.copy(alpha = 0.35f)
    val OverlayDarker = Color.Black.copy(alpha = 0.65f)
    val OverlayDarkest = Color.Black.copy(alpha = 0.85f)
    val OverlayLight = Color.Black.copy(alpha = 0.15f)
    val OverlayMedium = Color.Black.copy(alpha = 0.45f)
    val TextOnDarkPrimary = Color.White
    val TextOnDarkSecondary = Color.White.copy(alpha = 0.8f)
    val TextOnDarkTertiary = Color.White.copy(alpha = 0.6f)
}

