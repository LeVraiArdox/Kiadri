package com.lvardox.kiadri
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.lvardox.kiadri.ui.theme.backgroundDark
import com.lvardox.kiadri.ui.theme.backgroundDarkHighContrast
import com.lvardox.kiadri.ui.theme.backgroundDarkMediumContrast
import com.lvardox.kiadri.ui.theme.backgroundLight
import com.lvardox.kiadri.ui.theme.backgroundLightHighContrast
import com.lvardox.kiadri.ui.theme.backgroundLightMediumContrast
import com.lvardox.kiadri.ui.theme.errorContainerDark
import com.lvardox.kiadri.ui.theme.errorContainerDarkHighContrast
import com.lvardox.kiadri.ui.theme.errorContainerDarkMediumContrast
import com.lvardox.kiadri.ui.theme.errorContainerLight
import com.lvardox.kiadri.ui.theme.errorContainerLightHighContrast
import com.lvardox.kiadri.ui.theme.errorContainerLightMediumContrast
import com.lvardox.kiadri.ui.theme.errorDark
import com.lvardox.kiadri.ui.theme.errorDarkHighContrast
import com.lvardox.kiadri.ui.theme.errorDarkMediumContrast
import com.lvardox.kiadri.ui.theme.errorLight
import com.lvardox.kiadri.ui.theme.errorLightHighContrast
import com.lvardox.kiadri.ui.theme.errorLightMediumContrast
import com.lvardox.kiadri.ui.theme.inverseOnSurfaceDark
import com.lvardox.kiadri.ui.theme.inverseOnSurfaceDarkHighContrast
import com.lvardox.kiadri.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.lvardox.kiadri.ui.theme.inverseOnSurfaceLight
import com.lvardox.kiadri.ui.theme.inverseOnSurfaceLightHighContrast
import com.lvardox.kiadri.ui.theme.inverseOnSurfaceLightMediumContrast
import com.lvardox.kiadri.ui.theme.inversePrimaryDark
import com.lvardox.kiadri.ui.theme.inversePrimaryDarkHighContrast
import com.lvardox.kiadri.ui.theme.inversePrimaryDarkMediumContrast
import com.lvardox.kiadri.ui.theme.inversePrimaryLight
import com.lvardox.kiadri.ui.theme.inversePrimaryLightHighContrast
import com.lvardox.kiadri.ui.theme.inversePrimaryLightMediumContrast
import com.lvardox.kiadri.ui.theme.inverseSurfaceDark
import com.lvardox.kiadri.ui.theme.inverseSurfaceDarkHighContrast
import com.lvardox.kiadri.ui.theme.inverseSurfaceDarkMediumContrast
import com.lvardox.kiadri.ui.theme.inverseSurfaceLight
import com.lvardox.kiadri.ui.theme.inverseSurfaceLightHighContrast
import com.lvardox.kiadri.ui.theme.inverseSurfaceLightMediumContrast
import com.lvardox.kiadri.ui.theme.onBackgroundDark
import com.lvardox.kiadri.ui.theme.onBackgroundDarkHighContrast
import com.lvardox.kiadri.ui.theme.onBackgroundDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onBackgroundLight
import com.lvardox.kiadri.ui.theme.onBackgroundLightHighContrast
import com.lvardox.kiadri.ui.theme.onBackgroundLightMediumContrast
import com.lvardox.kiadri.ui.theme.onErrorContainerDark
import com.lvardox.kiadri.ui.theme.onErrorContainerDarkHighContrast
import com.lvardox.kiadri.ui.theme.onErrorContainerDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onErrorContainerLight
import com.lvardox.kiadri.ui.theme.onErrorContainerLightHighContrast
import com.lvardox.kiadri.ui.theme.onErrorContainerLightMediumContrast
import com.lvardox.kiadri.ui.theme.onErrorDark
import com.lvardox.kiadri.ui.theme.onErrorDarkHighContrast
import com.lvardox.kiadri.ui.theme.onErrorDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onErrorLight
import com.lvardox.kiadri.ui.theme.onErrorLightHighContrast
import com.lvardox.kiadri.ui.theme.onErrorLightMediumContrast
import com.lvardox.kiadri.ui.theme.onPrimaryContainerDark
import com.lvardox.kiadri.ui.theme.onPrimaryContainerDarkHighContrast
import com.lvardox.kiadri.ui.theme.onPrimaryContainerDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onPrimaryContainerLight
import com.lvardox.kiadri.ui.theme.onPrimaryContainerLightHighContrast
import com.lvardox.kiadri.ui.theme.onPrimaryContainerLightMediumContrast
import com.lvardox.kiadri.ui.theme.onPrimaryDark
import com.lvardox.kiadri.ui.theme.onPrimaryDarkHighContrast
import com.lvardox.kiadri.ui.theme.onPrimaryDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onPrimaryLight
import com.lvardox.kiadri.ui.theme.onPrimaryLightHighContrast
import com.lvardox.kiadri.ui.theme.onPrimaryLightMediumContrast
import com.lvardox.kiadri.ui.theme.onSecondaryContainerDark
import com.lvardox.kiadri.ui.theme.onSecondaryContainerDarkHighContrast
import com.lvardox.kiadri.ui.theme.onSecondaryContainerDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onSecondaryContainerLight
import com.lvardox.kiadri.ui.theme.onSecondaryContainerLightHighContrast
import com.lvardox.kiadri.ui.theme.onSecondaryContainerLightMediumContrast
import com.lvardox.kiadri.ui.theme.onSecondaryDark
import com.lvardox.kiadri.ui.theme.onSecondaryDarkHighContrast
import com.lvardox.kiadri.ui.theme.onSecondaryDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onSecondaryLight
import com.lvardox.kiadri.ui.theme.onSecondaryLightHighContrast
import com.lvardox.kiadri.ui.theme.onSecondaryLightMediumContrast
import com.lvardox.kiadri.ui.theme.onSurfaceDark
import com.lvardox.kiadri.ui.theme.onSurfaceDarkHighContrast
import com.lvardox.kiadri.ui.theme.onSurfaceDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onSurfaceLight
import com.lvardox.kiadri.ui.theme.onSurfaceLightHighContrast
import com.lvardox.kiadri.ui.theme.onSurfaceLightMediumContrast
import com.lvardox.kiadri.ui.theme.onSurfaceVariantDark
import com.lvardox.kiadri.ui.theme.onSurfaceVariantDarkHighContrast
import com.lvardox.kiadri.ui.theme.onSurfaceVariantDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onSurfaceVariantLight
import com.lvardox.kiadri.ui.theme.onSurfaceVariantLightHighContrast
import com.lvardox.kiadri.ui.theme.onSurfaceVariantLightMediumContrast
import com.lvardox.kiadri.ui.theme.onTertiaryContainerDark
import com.lvardox.kiadri.ui.theme.onTertiaryContainerDarkHighContrast
import com.lvardox.kiadri.ui.theme.onTertiaryContainerDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onTertiaryContainerLight
import com.lvardox.kiadri.ui.theme.onTertiaryContainerLightHighContrast
import com.lvardox.kiadri.ui.theme.onTertiaryContainerLightMediumContrast
import com.lvardox.kiadri.ui.theme.onTertiaryDark
import com.lvardox.kiadri.ui.theme.onTertiaryDarkHighContrast
import com.lvardox.kiadri.ui.theme.onTertiaryDarkMediumContrast
import com.lvardox.kiadri.ui.theme.onTertiaryLight
import com.lvardox.kiadri.ui.theme.onTertiaryLightHighContrast
import com.lvardox.kiadri.ui.theme.onTertiaryLightMediumContrast
import com.lvardox.kiadri.ui.theme.outlineDark
import com.lvardox.kiadri.ui.theme.outlineDarkHighContrast
import com.lvardox.kiadri.ui.theme.outlineDarkMediumContrast
import com.lvardox.kiadri.ui.theme.outlineLight
import com.lvardox.kiadri.ui.theme.outlineLightHighContrast
import com.lvardox.kiadri.ui.theme.outlineLightMediumContrast
import com.lvardox.kiadri.ui.theme.outlineVariantDark
import com.lvardox.kiadri.ui.theme.outlineVariantDarkHighContrast
import com.lvardox.kiadri.ui.theme.outlineVariantDarkMediumContrast
import com.lvardox.kiadri.ui.theme.outlineVariantLight
import com.lvardox.kiadri.ui.theme.outlineVariantLightHighContrast
import com.lvardox.kiadri.ui.theme.outlineVariantLightMediumContrast
import com.lvardox.kiadri.ui.theme.primaryContainerDark
import com.lvardox.kiadri.ui.theme.primaryContainerDarkHighContrast
import com.lvardox.kiadri.ui.theme.primaryContainerDarkMediumContrast
import com.lvardox.kiadri.ui.theme.primaryContainerLight
import com.lvardox.kiadri.ui.theme.primaryContainerLightHighContrast
import com.lvardox.kiadri.ui.theme.primaryContainerLightMediumContrast
import com.lvardox.kiadri.ui.theme.primaryDark
import com.lvardox.kiadri.ui.theme.primaryDarkHighContrast
import com.lvardox.kiadri.ui.theme.primaryDarkMediumContrast
import com.lvardox.kiadri.ui.theme.primaryLight
import com.lvardox.kiadri.ui.theme.primaryLightHighContrast
import com.lvardox.kiadri.ui.theme.primaryLightMediumContrast
import com.lvardox.kiadri.ui.theme.scrimDark
import com.lvardox.kiadri.ui.theme.scrimDarkHighContrast
import com.lvardox.kiadri.ui.theme.scrimDarkMediumContrast
import com.lvardox.kiadri.ui.theme.scrimLight
import com.lvardox.kiadri.ui.theme.scrimLightHighContrast
import com.lvardox.kiadri.ui.theme.scrimLightMediumContrast
import com.lvardox.kiadri.ui.theme.secondaryContainerDark
import com.lvardox.kiadri.ui.theme.secondaryContainerDarkHighContrast
import com.lvardox.kiadri.ui.theme.secondaryContainerDarkMediumContrast
import com.lvardox.kiadri.ui.theme.secondaryContainerLight
import com.lvardox.kiadri.ui.theme.secondaryContainerLightHighContrast
import com.lvardox.kiadri.ui.theme.secondaryContainerLightMediumContrast
import com.lvardox.kiadri.ui.theme.secondaryDark
import com.lvardox.kiadri.ui.theme.secondaryDarkHighContrast
import com.lvardox.kiadri.ui.theme.secondaryDarkMediumContrast
import com.lvardox.kiadri.ui.theme.secondaryLight
import com.lvardox.kiadri.ui.theme.secondaryLightHighContrast
import com.lvardox.kiadri.ui.theme.secondaryLightMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceBrightDark
import com.lvardox.kiadri.ui.theme.surfaceBrightDarkHighContrast
import com.lvardox.kiadri.ui.theme.surfaceBrightDarkMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceBrightLight
import com.lvardox.kiadri.ui.theme.surfaceBrightLightHighContrast
import com.lvardox.kiadri.ui.theme.surfaceBrightLightMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerDark
import com.lvardox.kiadri.ui.theme.surfaceContainerDarkHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerDarkMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerHighDark
import com.lvardox.kiadri.ui.theme.surfaceContainerHighDarkHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerHighDarkMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerHighLight
import com.lvardox.kiadri.ui.theme.surfaceContainerHighLightHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerHighLightMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerHighestDark
import com.lvardox.kiadri.ui.theme.surfaceContainerHighestDarkHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerHighestLight
import com.lvardox.kiadri.ui.theme.surfaceContainerHighestLightHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerHighestLightMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLight
import com.lvardox.kiadri.ui.theme.surfaceContainerLightHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLightMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLowDark
import com.lvardox.kiadri.ui.theme.surfaceContainerLowDarkHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLowDarkMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLowLight
import com.lvardox.kiadri.ui.theme.surfaceContainerLowLightHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLowLightMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLowestDark
import com.lvardox.kiadri.ui.theme.surfaceContainerLowestDarkHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLowestLight
import com.lvardox.kiadri.ui.theme.surfaceContainerLowestLightHighContrast
import com.lvardox.kiadri.ui.theme.surfaceContainerLowestLightMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceDark
import com.lvardox.kiadri.ui.theme.surfaceDarkHighContrast
import com.lvardox.kiadri.ui.theme.surfaceDarkMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceDimDark
import com.lvardox.kiadri.ui.theme.surfaceDimDarkHighContrast
import com.lvardox.kiadri.ui.theme.surfaceDimDarkMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceDimLight
import com.lvardox.kiadri.ui.theme.surfaceDimLightHighContrast
import com.lvardox.kiadri.ui.theme.surfaceDimLightMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceLight
import com.lvardox.kiadri.ui.theme.surfaceLightHighContrast
import com.lvardox.kiadri.ui.theme.surfaceLightMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceVariantDark
import com.lvardox.kiadri.ui.theme.surfaceVariantDarkHighContrast
import com.lvardox.kiadri.ui.theme.surfaceVariantDarkMediumContrast
import com.lvardox.kiadri.ui.theme.surfaceVariantLight
import com.lvardox.kiadri.ui.theme.surfaceVariantLightHighContrast
import com.lvardox.kiadri.ui.theme.surfaceVariantLightMediumContrast
import com.lvardox.kiadri.ui.theme.tertiaryContainerDark
import com.lvardox.kiadri.ui.theme.tertiaryContainerDarkHighContrast
import com.lvardox.kiadri.ui.theme.tertiaryContainerDarkMediumContrast
import com.lvardox.kiadri.ui.theme.tertiaryContainerLight
import com.lvardox.kiadri.ui.theme.tertiaryContainerLightHighContrast
import com.lvardox.kiadri.ui.theme.tertiaryContainerLightMediumContrast
import com.lvardox.kiadri.ui.theme.tertiaryDark
import com.lvardox.kiadri.ui.theme.tertiaryDarkHighContrast
import com.lvardox.kiadri.ui.theme.tertiaryDarkMediumContrast
import com.lvardox.kiadri.ui.theme.tertiaryLight
import com.lvardox.kiadri.ui.theme.tertiaryLightHighContrast
import com.lvardox.kiadri.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun KiadriTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

