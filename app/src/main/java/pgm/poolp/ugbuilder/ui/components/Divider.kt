package pgm.poolp.ugbuilder.ui.components

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pgm.poolp.ugbuilder.ui.theme.UGBuilderTheme

@Composable
fun UGBuilderDivider(
    modifier: Modifier = Modifier,
    color: Color = UGBuilderTheme.colors.background,
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = thickness,
        startIndent = startIndent
    )
}

private const val DividerAlpha = 0.12f