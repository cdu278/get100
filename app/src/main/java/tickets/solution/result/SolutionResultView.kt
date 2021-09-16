package tickets.solution.result

import androidx.compose.animation.core.Animatable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import tickets.solution.result.FontColor.WhenNotSolved
import tickets.solution.result.FontColor.WhenSolved
import tickets.solution.result.SolutionResultViewState.NotReady
import tickets.solution.result.SolutionResultViewState.Ready
import tickets.solution.result.SolutionResultViewState.Ready.Sign
import tickets.solution.result.SolutionResultViewState.Ready.Sign.AlmostEqualTo
import tickets.solution.result.SolutionResultViewState.Ready.Sign.EqualTo
import tickets.solution.result.SolutionResultViewState.Ready.Value
import tickets.solution.result.SolutionResultViewState.Ready.Value.Defined
import tickets.solution.result.SolutionResultViewState.Ready.Value.Undefined

@Composable
fun SolutionResultView(
    viewModel: SolutionResultViewModel = getViewModel<SolutionResultViewModelImpl>(),
) {
    val state by viewModel.state.collectAsState()
    var text by remember { mutableStateOf(state.text) }
    var fontColor by remember { mutableStateOf(state.fontColor) }
    val alpha = remember { Animatable(0f) }
    if (state is Ready) {
        LaunchedEffect(state) {
            alpha.animateTo(0f)
            text = state.text
            fontColor = state.fontColor
            alpha.animateTo(1f)
        }
    }
    Text(
        text,
        color = fontColor.value,
        textAlign = TextAlign.Center,
        fontSize = 32.sp,
        modifier = Modifier.graphicsLayer(alpha = alpha.value),
    )
}

private val SolutionResultViewState.text: String
    get() = when(this) {
        is Ready -> "${sign.text}\n${value.text}"
        is NotReady -> "=\n0" // Dummy
    }

private val Value.text: String
    get() = when (this) {
        is Undefined -> "???"
        is Defined -> this.value.toString()
    }

private val Sign.text: String
    get() = when (this) {
        EqualTo -> "="
        AlmostEqualTo -> "≈"
    }

private enum class FontColor { WhenSolved, WhenNotSolved }

private val SolutionResultViewState.fontColor: FontColor
    get() = when (this) {
        is Ready -> if (this.solved) WhenSolved else WhenNotSolved
        is NotReady -> WhenSolved // Dummy
    }

private val FontColor.value: Color
    @Composable
    get() = when (this) {
        WhenSolved -> MaterialTheme.colors.primary
        WhenNotSolved -> MaterialTheme.colors.onSurface
    }
