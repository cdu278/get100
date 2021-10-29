package cdu145.tickets.solution.gaps

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import cdu145.tickets.digits.DigitCard
import cdu145.loadable.Loadable
import cdu145.loadable.Loadable.Ready
import cdu145.tickets.solution.Solution
import cdu145.tickets.solution.signs.SolutionSign
import cdu145.tickets.solution.signs.SolutionSign.*
import cdu145.util.CachedValues
import cdu145.tickets.R
import cdu145.ui.composable.CircleButton

private val highlightedGapColor: Color
    @Composable
    get() = MaterialTheme.colors.secondaryVariant

@Composable
fun SolutionGapButtons(
    buttonsElevation: ButtonElevation,
    viewModel: SolutionGapsViewModel = getViewModel(),
) {
    val highlightedPosition by viewModel.highlightedPosition.collectAsState()
    val shownSolution by viewModel.shownSolution.collectAsState()
    val justOpenedPosition by viewModel.justOpenedPosition.collectAsState()
    repeat(5) { i ->
        SolutionGapButton.View(
            position = i,
            sign = shownSolution.signAtOrNone(i),
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

private fun Loadable<Solution>.signAtOrNone(position: Int): SolutionSign {
    return (this as? Ready)?.value?.signAt(position) ?: None
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
        elevation: ButtonElevation,
        backgroundColor: Color,
        modifier: Modifier,
        viewModel: SolutionGapsViewModel = getViewModel(),
    ) {
        CircleButton(
            onClick = { viewModel.highlightGapAt(position) },
            colors = buttonColors(backgroundColor = backgroundColor),
            elevation = elevation,
            modifier = modifier.size(Size),
        ) {
            Text(sign.text)
        }
    }

    private val SolutionSign.text: String
        @Composable
        get() = when (this) {
            Plus -> stringResource(R.string.signs_plus)
            Minus -> stringResource(R.string.signs_minus)
            Times -> stringResource(R.string.signs_times)
            Div -> stringResource(R.string.signs_div)
            None -> ""
        }

    @Composable
    private fun paddingOfButtonAt(position: Int): Dp {
        return DigitCard.Paddings[position] + DigitCard.Width - Overlap
    }
}