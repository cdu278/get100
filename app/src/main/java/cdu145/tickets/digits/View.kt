package cdu145.tickets.digits

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cdu145.loadable.Loadable
import cdu145.loadable.Loadable.Ready
import cdu145.tickets.solution.gaps.SolutionGapButton
import cdu145.util.CachedValues
import org.koin.androidx.compose.getViewModel

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
        DigitCard(
            digit = shownDigits[i],
            elevation = cardsElevation,
            alpha = { digitsAlpha.value },
            modifier = Modifier.padding(start = DigitCard.Paddings[i]),
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

    @SuppressLint("ComposableNaming")
    @Composable
    operator fun invoke(
        digit: Int,
        elevation: Dp,
        alpha: () -> Float,
        modifier: Modifier = Modifier,
    ) {
        Surface(
            shape = RoundedCornerShape(CornerSize),
            elevation = elevation,
            modifier = modifier,
        ) {
            Box(
                contentAlignment = Center,
                modifier = Modifier.size(Width, Height),
            ) {
                Text(
                    text = digit.toString(),
                    fontSize = FontSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer { this.alpha = alpha() },
                )
            }
        }
    }

    private fun paddingOfCardAt(position: Int): Dp {
        return (Width + SolutionGapButton.Size - SolutionGapButton.Overlap * 2) * position
    }
}