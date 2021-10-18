package cdu145.tickets.ticket

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import org.koin.androidx.compose.getViewModel
import cdu145.tickets.digits.TicketDigits
import cdu145.tickets.digits.TicketDigits.Zeros
import cdu145.loadable.Loadable
import cdu145.loadable.Loadable.Ready
import kotlin.random.Random

@Composable
fun AnimatedTicketView(
    viewModel: TicketViewModel = getViewModel(),
    elevation: Dp,
) {
    val digits by viewModel.digits.collectAsState()
    var shownDigits by remember { mutableStateOf<TicketDigits>(Zeros) }
    val translationRatio = remember { Animatable(1f) }
    var rotation by remember { mutableStateOf(0f) }
    if (digits is Ready) {
        LaunchedEffect(digits.value) {
            translationRatio.animateTo(1f)
            shownDigits = digits.value
            rotation = randomRotation()
            translationRatio.animateTo(0f)
        }
    }
    TicketView(
        shownDigits,
        elevation,
        numberRotation = rotation,
        animationTransitionRatio = translationRatio.value,
    )
}

private val Loadable<TicketDigits>.value: TicketDigits
    get() = (this as Ready).value

private const val rotationAmplitude = 2f

private fun randomRotation(): Float {
    return (Random.nextFloat() * rotationAmplitude * 2) - rotationAmplitude
}