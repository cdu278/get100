package tickets.ticket

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import tickets.digits.DigitsKey
import tickets.digits.TicketDigits
import tickets.digits.TicketDigits.Zeros
import tickets.loadable.Loadable
import tickets.loadable.Loadable.Ready
import kotlin.random.Random

private val height = 195.dp

@Composable
fun TicketView(
    viewModel: TicketViewModel = getViewModel(),
    elevation: Dp,
) {
    val digits by viewModel.digits.collectAsState()
    var shownDigits by remember { mutableStateOf<TicketDigits>(Zeros) }
    val translationRatio = remember { Animatable(1f) }
    var rotation by remember { mutableStateOf(0f) }
    if (digits is Ready) {
        LaunchedEffect(DigitsKey(digits.value)) {
            translationRatio.animateTo(1f)
            shownDigits = digits.value
            rotation = randomRotation(amplitude = 2f)
            translationRatio.animateTo(0f)
        }
    }
    Surface(
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        color = MaterialTheme.colors.primary,
        elevation = elevation,
        modifier = Modifier
            .size(width = 170.dp, height)
            .graphicsLayer(translationY = heightPx * translationRatio.value),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_ticket_back),
            contentDescription = null,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(Modifier.height(40.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = numberText(shownDigits),
                    fontFamily = FontFamily(Font(R.font.averia_regular)),
                    color = Color(0xFF6F655C),
                    fontSize = 20.sp,
                    modifier = Modifier.graphicsLayer(rotationZ = rotation)
                )
            }
        }
    }
}

private val Loadable<TicketDigits>.value: TicketDigits
    get() = (this as Ready).value

private fun numberText(digits: TicketDigits): String {
    return buildString {
        repeat(5) { i ->
            append(digits[i])
            append(" ")
        }
        append(digits[5])
    }
}

private val heightPx: Float
    @Composable
    get() = with(LocalDensity.current) { height.toPx() }

private fun randomRotation(amplitude: Float): Float {
    return (Random.nextFloat() * amplitude * 2) - amplitude
}