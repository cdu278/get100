package tickets.solution.gap

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import tickets.digits.DigitCard
import tickets.solution.gap.ShownSolutionState.Ready
import tickets.solution.signs.ArithmeticSign.*
import tickets.solution.signs.SolutionSign
import tickets.solution.signs.get
import tickets.ui.CircleButton
import tickets.util.CachedValues

private val highlightedGapColor: Color
    @Composable
    get() = MaterialTheme.colors.secondaryVariant

@Composable
fun SolutionGapButtons(
    buttonsElevation: ButtonElevation,
    viewModel: SolutionGapsViewModel = getViewModel(),
) {
    val highlightedPosition by viewModel.highlightedPosition.collectAsState()
    val shownSolutionState by viewModel.shownSolutionState.collectAsState()
    val enabled by viewModel.enabled.collectAsState()
    val justOpenedPosition by viewModel.justOpenedPosition.collectAsState()
    repeat(5) { i ->
        SolutionGapButton.View(
            position = i,
            sign = shownSolutionState.signAtOrNone(i),
            enabled = enabled,
            elevation = buttonsElevation,
            backgroundColor = when (val gapPosition = i.asGapPosition()) {
                justOpenedPosition -> justOpenedGapBackgroundColor(gapPosition, highlightedPosition)
                highlightedPosition -> highlightedGapColor
                else -> MaterialTheme.colors.surface
            },
            modifier = Modifier.padding(
                start = SolutionGapButton.Paddings[i],
                top = (DigitCard.Height - SolutionGapButton.Size) / 2,
            ),
        )
    }
}

private fun ShownSolutionState.signAtOrNone(position: Int): SolutionSign {
    return (this as? Ready)?.let { it.solution[position] } ?: NONE
}

private fun Int.asGapPosition(): GapPosition = GapPosition.Some(this)

@Composable
private fun justOpenedGapBackgroundColor(
    position: GapPosition,
    highlightedPosition: GapPosition,
): Color {
    val initialColor = MaterialTheme.colors.secondary
    val targetColor = if (position == highlightedPosition) {
        highlightedGapColor
    } else {
        MaterialTheme.colors.surface
    }
    val color = remember(position) { Animatable(initialColor) }
    LaunchedEffect(position) {
        color.run {
            snapTo(initialColor)
            animateTo(targetColor, animationSpec = tween(durationMillis = 3_500))
        }
    }
    return color.value
}

object SolutionGapButton {

    val Size = 32.dp

    val Overlap = 6.dp

    val Paddings = CachedValues({ position -> paddingOfButtonAt(position) })

    @Composable
    fun View(
        position: Int,
        sign: SolutionSign,
        enabled: Boolean,
        elevation: ButtonElevation,
        backgroundColor: Color,
        modifier: Modifier,
        viewModel: SolutionGapsViewModel = getViewModel(),
    ) {
        CircleButton(
            onClick = { viewModel.highlightGapAt(position) },
            enabled = enabled,
            colors = buttonColors(backgroundColor = backgroundColor),
            elevation = elevation,
            modifier = modifier.size(Size),
        ) {
            Text(sign.text)
        }
    }

    private val SolutionSign.text: String
        @Composable
        get() = when (this.value) {
            PLUS -> "+"
            MINUS -> "-"
            TIMES -> "*"
            DIV -> "/"
            NONE -> ""
        }

    @Composable
    private fun paddingOfButtonAt(position: Int): Dp {
        return DigitCard.Paddings[position] + DigitCard.Width - Overlap
    }
}