package tickets.solution.result

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import tickets.solution.result.SolutionResultViewState.Value.Sign
import tickets.solution.result.SolutionResultViewState.Value.Sign.AlmostEqualTo
import tickets.solution.result.SolutionResultViewState.Value.Sign.EqualTo

@Composable
fun SolutionResultView(
    viewModel: SolutionResultViewModel = viewModel<SolutionResultViewModelImpl>(),
) {
    val state by viewModel.state.collectAsState(initial = SolutionResultViewState.Preview)
    val alpha by animateFloatAsState(if (state.ready) 1f else 0f)
    Text(
        text = state.text,
        textAlign = TextAlign.Center,
        fontSize = 32.sp,
        modifier = Modifier.graphicsLayer(alpha = alpha),
    )
}

private val SolutionResultViewState.text: String
    get() = this.value.run { "${sign.text}\n$value" }

private val Sign.text: String
    get() = when (this) {
        EqualTo -> "="
        AlmostEqualTo -> "≈"
    }