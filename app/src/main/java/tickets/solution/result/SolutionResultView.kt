package tickets.solution.result

import androidx.compose.animation.core.Animatable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import tickets.loadable.Loadable.Ready
import tickets.solution.result.ResultTextColor.WhenNotSolved
import tickets.solution.result.ResultTextColor.WhenSolved
import tickets.solution.result.SolutionResultViewState.NotSolved
import tickets.solution.result.SolutionResultViewState.NotSolved.Sign.AlmostEqualTo
import tickets.solution.result.SolutionResultViewState.NotSolved.Sign.EqualTo
import tickets.solution.result.SolutionResultViewState.NotSolved.Value.Defined
import tickets.solution.result.SolutionResultViewState.NotSolved.Value.Undefined
import tickets.solution.result.SolutionResultViewState.Solved
import tickets.vibration.Vibration

@Composable
fun SolutionResultView(
    viewModel: SolutionResultViewModel = getViewModel(),
    vibration: Vibration = get(),
) {
    val loadableState by viewModel.state.collectAsState()
    var text by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(WhenNotSolved) }
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    if (loadableState is Ready) {
        val state = (loadableState as Ready).value
        LaunchedEffect(state) {
            alpha.animateTo(0f)
            when (state) {
                is Solved -> {
                    text = "=\n100"
                    color = WhenSolved
                    launch { scale.animateTo(1.6f) }
                    vibration.oneShot()
                }
                is NotSolved -> {
                    alpha.animateTo(0f)
                    text = createTextFrom(state)
                    color = WhenNotSolved
                    scale.snapTo(1f)
                }
            }
            alpha.animateTo(1f)
        }
    }
    Text(
        text,
        color = color.value,
        textAlign = TextAlign.Center,
        fontSize = 32.sp,
        modifier = Modifier.graphicsLayer(
            alpha = alpha.value,
            transformOrigin = TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = 0f),
            scaleX = scale.value,
            scaleY = scale.value,
        ),
    )
}

private fun createTextFrom(state: NotSolved): String = with(state) { "${sign.text}\n${value.text}"}

private val NotSolved.Value.text: String
    get() = when (this) {
        is Undefined -> "???"
        is Defined -> this.value.toString()
    }

private val NotSolved.Sign.text: String
    get() = when (this) {
        EqualTo -> "="
        AlmostEqualTo -> "≈"
    }

private val ResultTextColor.value: Color
    @Composable
    get() = when (this) {
        WhenSolved -> MaterialTheme.colors.primary
        WhenNotSolved -> MaterialTheme.colors.onSurface
    }
