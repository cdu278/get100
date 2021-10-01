package tickets.hint

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import tickets.loadable.Loadable
import tickets.loadable.Loadable.NotReady
import tickets.loadable.Loadable.Ready

@Composable
fun HintButton(
    viewModel: HintButtonViewModel = getViewModel(),
) {
    val availableCount by viewModel.availableCount.collectAsState()
    val enabled by viewModel.enabled.collectAsState()
    val shownRatio by animateFloatAsState(targetValue = if (enabled) 1f else 0f)
    Button(
        onClick = { viewModel.useHint() },
        modifier = Modifier.graphicsLayer(alpha = shownRatio),
    ) {
        Text(text(availableCount))
    }
}

@Composable
private fun text(availableCount: Loadable<Int>): String {
    return when (availableCount) {
        is NotReady -> stringResource(R.string.hintButton_notReadyText)
        is Ready -> stringResource(R.string.hintButton_readyText, availableCount.value)
    }
}