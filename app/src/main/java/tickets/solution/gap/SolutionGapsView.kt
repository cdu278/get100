package tickets.solution.gap

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import tickets.digits.DigitCard
import tickets.solution.signs.ArithmeticSign.*
import tickets.solution.signs.SolutionSign
import tickets.solution.signs.SolutionSigns
import tickets.solution.signs.SolutionSignsFlow
import tickets.solution.signs.get
import tickets.solution.signs.position.SignPosition
import tickets.ui.CircleButton
import tickets.util.CachedValues

@Composable
fun SolutionGapButtons(
    buttonsElevation: ButtonElevation,
    viewModel: SolutionGapsViewModel = getViewModel<SolutionGapsViewModelImpl>(),
    solutionSignsFlow: Flow<SolutionSigns> = get(SolutionSignsFlow),
    highlightedSignPositionFlow: Flow<SignPosition> = get(HighlightedSignPositionFlow),
) {
    val enabled by viewModel.enabled.collectAsState(initial = true)
    val solutionSigns by solutionSignsFlow.collectAsState(initial = SolutionSigns.Empty)
    val highlightedSignPosition by highlightedSignPositionFlow
        .collectAsState(initial = SignPosition.First)
    repeat(5) { i ->
        SolutionGapButton.View(
            position = i,
            sign = solutionSigns[i],
            enabled = enabled,
            highlighted = highlightedSignPosition.value == i,
            elevation = buttonsElevation,
            modifier = Modifier.padding(
                start = SolutionGapButton.Paddings[i],
                top = (DigitCard.Height - SolutionGapButton.Size) / 2,
            ),
        )
    }
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
        highlighted: Boolean,
        elevation: ButtonElevation,
        modifier: Modifier,
        viewModel: SolutionGapsViewModel = getViewModel<SolutionGapsViewModelImpl>(),
    ) {
        CircleButton(
            onClick = { viewModel.highlightGapAt(position) },
            enabled = enabled,
            colors = if (highlighted) {
                buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
            } else {
                buttonColors(backgroundColor = MaterialTheme.colors.surface)
            },
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