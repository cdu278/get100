package tickets.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick,
        modifier = Modifier.width(96.dp),
        content = content,
    )
}