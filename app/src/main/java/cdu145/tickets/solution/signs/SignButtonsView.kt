package cdu145.tickets.solution.signs

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import cdu145.tickets.solution.signs.SolutionSign.*
import cdu145.ui.composable.CircleButton

@Composable
fun SignButtons(
    spaceBetween: Dp,
    viewModel: SignButtonsViewModel = getViewModel(),
) {
    Row {
        val shown by viewModel.shown.collectAsState()
        val shownRatio by animateFloatAsState(if (shown) 1f else 0f)
        SolutionSign.values().forEachIndexed { index, sign ->
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
    viewModel: SignButtonsViewModel = getViewModel(),
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
    get() = when (this) {
        Plus -> stringResource(R.string.signs_plus)
        Minus -> stringResource(R.string.signs_minus)
        Times -> stringResource(R.string.signs_times)
        Div -> stringResource(R.string.signs_div)
        None -> stringResource(R.string.signs_none)
    }