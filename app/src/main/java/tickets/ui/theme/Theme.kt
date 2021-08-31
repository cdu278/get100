package tickets.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun ComposeTicketsTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = lightColors(
            primary = Purple500,
            primaryVariant = Purple700,
            secondary = Teal200,
            background = Grey200,
        ),
        typography = Typography,
        shapes = Shapes,
        content,
    )
}