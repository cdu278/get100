@file:Suppress("unused")

package tickets.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnScope.Margin(height: Dp) {
    Spacer(Modifier.height(height))
}

@Composable
fun RowScope.Margin(width: Dp) {
    Spacer(Modifier.width(width))
}