package tickets.hint

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import tickets.hint.AvailableCountState.NotReady
import tickets.hint.AvailableCountState.Ready

@Composable
fun HintButton(
    viewModel: HintButtonViewModel = getViewModel(),
) {
    val enabled by viewModel.enabled.collectAsState()
    val availableCountState by viewModel.availableCountState.collectAsState()
    Button(
        onClick = { viewModel.useHint() },
        enabled = enabled,
    ) {
        Text(text = availableCountState.createText())
    }
}

@Composable
private fun AvailableCountState.createText(): String {
    return when (this) {
        is NotReady -> stringResource(R.string.hintButton_notReadyText)
        is Ready -> stringResource(R.string.hintButton_readyText, this.value)
    }
}