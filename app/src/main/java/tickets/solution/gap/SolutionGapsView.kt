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
import androidx.lifecycle.viewmodel.compose.viewModel
import tickets.digits.DigitCard
import tickets.solution.signs.ArithmeticSign
import tickets.solution.signs.ArithmeticSign.*
import tickets.solution.signs.SolutionSign
import tickets.ui.CircleButton
import tickets.util.CachedValues

@Composable
fun SolutionGapButtons(
    buttonsElevation: ButtonElevation,
    viewModel: SolutionGapsViewModel = viewModel<SolutionGapsViewModelImpl>(),
) {
    val state by viewModel.state.collectAsState(initial = SolutionGapsState.Preview)
    repeat(5) { i ->
        SolutionGapButton.View(
            position = i,
            sign = ArithmeticSign.values()[i],
            enabled = state.enabled,
            highlighted = i == state.highlightedPosition,
            elevation = buttonsElevation,
            modifier = Modifier.padding(
                start = SolutionGapButton.Paddings[i],
                top = (DigitCard.Height - SolutionGapButton.Size) / 2,
            ),
            viewModel = SolutionGapsViewModel.Preview,
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
        viewModel: SolutionGapsViewModel = viewModel<SolutionGapsViewModelImpl>(),
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