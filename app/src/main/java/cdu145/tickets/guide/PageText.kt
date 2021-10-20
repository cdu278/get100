package cdu145.tickets.guide

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import org.koin.androidx.compose.getViewModel

@Composable
fun PageText(
    viewModel: GuideDialogViewModel = getViewModel(),
    pages: List<Page> = remember { Guide.scope.get(Pages) },
    horizontalPadding: Dp,
) {
    val pagePosition by viewModel.pagePosition.collectAsState()
    var shownPagePosition by remember { mutableStateOf(0) }
    val alpha = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(pagePosition) {
        alpha.animateTo(0f)
        shownPagePosition = pagePosition
        alpha.animateTo(1f)
    }

    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        val textStyle = MaterialTheme.typography.body2
        ProvideTextStyle(textStyle) {
            Text(
                text = pages[shownPagePosition].text,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .graphicsLayer(alpha = alpha.value)
                    .padding(horizontal = horizontalPadding),
            )
        }
    }
}