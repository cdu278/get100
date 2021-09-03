package tickets.digits

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import tickets.solution.gap.SolutionGapButton
import tickets.ui.animationAware
import tickets.util.CachedValues

private const val animationDuration = 500

@Composable
fun DigitCards(
    cardsElevation: Dp,
    viewModel: DigitCardsViewModel = getViewModel<DigitCardsViewModelImpl>(),
) {
    val state by remember { viewModel.state.animationAware(animationDuration) }
        .collectAsState(initial = DigitCardsState.Preview)
    val digitsAlpha by animateFloatAsState(
        targetValue = if (state.loaded) 1f else 0f,
        animationSpec = tween(animationDuration),
    )
    repeat(6) { i ->
        DigitCard.View(
            digit = state.digits[i],
            padding = DigitCard.Paddings[i],
            elevation = cardsElevation,
            digitsAlpha,
        )
    }
}

object DigitCard {

    val Width = 36.dp
    val Height = 48.dp

    private val CornerSize = 4.dp

    private val FontSize = 26.sp

    val Paddings = CachedValues({ position -> paddingOfCardAt(position) })

    @Composable
    fun View(
        digit: TicketDigit,
        padding: Dp,
        elevation: Dp,
        alpha: Float,
    ) {
        Surface(
            modifier = Modifier.padding(start = padding),
            shape = RoundedCornerShape(CornerSize),
            elevation = elevation,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(Width)
                    .height(Height),
            ) {
                Text(
                    text = digit.text,
                    fontSize = FontSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer(alpha = alpha),
                )
            }
        }
    }

    private val TicketDigit.text: String
        get() = this.value.toString()

    private fun paddingOfCardAt(position: Int): Dp {
        return (Width + SolutionGapButton.Size - SolutionGapButton.Overlap * 2) * position
    }
}