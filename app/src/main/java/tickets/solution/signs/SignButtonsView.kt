package tickets.solution.signs

import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import tickets.solution.signs.ArithmeticSign.*
import tickets.ui.CircleButton

private val Size = 42.dp

@Composable
fun SignButtons(
    spaceBetween: Dp,
    viewModel: SignButtonsViewModel = getViewModel<SignButtonsViewModelImpl>(),
) {
    Row {
        val shown by viewModel.shown.collectAsState(initial = true)
        val buttonSize by animateDpAsState(if (shown) Size else 0.dp)
        ArithmeticSign.values().forEachIndexed { index, sign ->
            SignButton(
                sign,
                buttonSize,
            )
            if (index < 4) {
                Spacer(Modifier.width(spaceBetween))
            }
        }
    }
}

@Composable
private fun SignButton(
    sign: SolutionSign,
    size: Dp,
    viewModel: SignButtonsViewModel = getViewModel<SignButtonsViewModelImpl>(),
) {
    CircleButton(
        onClick = { viewModel.chooseSign(sign) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
        ),
        modifier = Modifier.size(size),
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