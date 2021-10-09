package tickets.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tickets.digits.TicketDigits
import tickets.digits.key.key
import tickets.ui.theme.AveriaFontFamily
import tickets.ui.theme.InkBlue
import tickets.ui.theme.InkGreen

private val width = 170.dp
private val height = 195.dp

private val cornerRadius = 18.dp

@Composable
fun TicketView(
    digits: TicketDigits,
    elevation: Dp,
    numberRotation: Float,
    animationTransitionRatio: Float,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width, height)
            .graphicsLayer {
                shape = TicketShape(cornerRadius)
                shadowElevation = elevation.toPx()
                translationY = height.toPx() * translationRatio(animationTransitionRatio)
                clip = true
            }
            .background(MaterialTheme.colors.primary)
            .drawBehind {
                scale(0.9f) {
                    drawPath(
                        ticketOutlinePath(size, cornerRadius.toPx()),
                        color = InkGreen,
                        style = Stroke(
                            width = 3.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(20f, 20f),
                            ),
                        ),
                    )
                }
            },
    ) {
        Text(
            text = remember(digits.key) { numberText(digits) },
            fontFamily = AveriaFontFamily,
            color = InkBlue,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 24.dp)
                .graphicsLayer(rotationZ = numberRotation),
        )
    }
}

private fun numberText(digits: TicketDigits): String {
    return buildString {
        repeat(5) { i ->
            append(digits[i])
            append(" ")
        }
        append(digits[5])
    }
}

private const val baseTranslationRatio = 0.075f

private fun translationRatio(animationTranslationRatio: Float): Float {
    return ((1f - baseTranslationRatio) * animationTranslationRatio) + baseTranslationRatio
}