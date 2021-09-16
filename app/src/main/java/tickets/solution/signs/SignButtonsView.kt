package tickets.solution.signs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import tickets.solution.signs.ArithmeticSign.*
import tickets.ui.CircleButton

@Composable
fun SignButtons(
    spaceBetween: Dp,
    viewModel: SignButtonsViewModel = getViewModel<SignButtonsViewModelImpl>(),
) {
    Row {
        val shown by viewModel.shown.collectAsState()
        val shownRatio by animateFloatAsState(if (shown) 1f else 0f)
        ArithmeticSign.values().forEachIndexed { index, sign ->
            SignButton(sign, shownRatio)
            if (index < 4) {
                Spacer(Modifier.width(spaceBetween))
            }
        }
    }
}

private val Size = 42.dp

@Composable
private fun SignButton(
    sign: SolutionSign,
    shownRatio: Float,
    viewModel: SignButtonsViewModel = getViewModel<SignButtonsViewModelImpl>(),
) {
    CircleButton(
        onClick = { viewModel.chooseSign(sign) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
        ),
        modifier = Modifier
            .size(Size)
            .graphicsLayer(scaleX = shownRatio, scaleY = shownRatio),
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
        NONE -> "␣"
    }