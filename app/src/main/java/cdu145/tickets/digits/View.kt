package cdu145.tickets.digits

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import cdu145.loadable.Loadable
import cdu145.loadable.Loadable.Ready
import cdu145.tickets.solution.gaps.SolutionGapButton
import cdu145.util.CachedValues

@Composable
fun DigitCards(
    cardsElevation: Dp,
    viewModel: DigitCardsViewModel = getViewModel(),
) {
    val digits by viewModel.digits.collectAsState()
    var shownDigits: TicketDigits by remember { mutableStateOf(TicketDigits.Zeros) }
    val digitsAlpha = remember { Animatable(initialValue = 0f) }
    if (digits is Ready) {
        LaunchedEffect(digits.value) {
            digitsAlpha.animateTo(0f)
            shownDigits = digits.value
            digitsAlpha.animateTo(1f)
        }
    }
    repeat(6) { i ->
        DigitCard.View(
            digit = shownDigits[i],
            padding = DigitCard.Paddings[i],
            elevation = cardsElevation,
            alpha = digitsAlpha.value,
        )
    }
}

private val Loadable<TicketDigits>.value: TicketDigits
    get() = (this as Ready).value

object DigitCard {

    val Width = 36.dp
    val Height = 48.dp

    private val CornerSize = 4.dp

    private val FontSize = 26.sp

    val Paddings = CachedValues({ position -> paddingOfCardAt(position) })

    @Composable
    fun View(
        digit: Int,
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
                    text = digit.toString(),
                    fontSize = FontSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer(alpha = alpha),
                )
            }
        }
    }

    private fun paddingOfCardAt(position: Int): Dp {
        return (Width + SolutionGapButton.Size - SolutionGapButton.Overlap * 2) * position
    }
}