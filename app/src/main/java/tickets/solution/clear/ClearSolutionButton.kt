package tickets.solution.clear

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import tickets.ui.Margin

@Composable
fun ClearSolutionButton(
    viewModel: ClearSolutionButtonViewModel = getViewModel(),
) {
    val shown by viewModel.shown.collectAsState()
    val shownRatio by animateFloatAsState(targetValue = if (shown) 1f else 0f)
    Button(
        onClick = { viewModel.clearSolution() },
        modifier = Modifier.graphicsLayer(alpha = shownRatio),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_clear),
            contentDescription = null,
        )
        Margin(4.dp)
        Text(stringResource(R.string.clearSolutionButton_text))
    }
}