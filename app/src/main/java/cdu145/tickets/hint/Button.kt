package cdu145.tickets.hint

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import cdu145.loadable.Loadable
import cdu145.loadable.Loadable.Ready

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HintButton(
    viewModel: HintButtonViewModel = getViewModel(),
) {
    val availableCount by viewModel.availableCount.collectAsState()
    val enabled by viewModel.enabled.collectAsState()
    val shownRatio by animateFloatAsState(targetValue = if (enabled) 1f else 0f)
    FloatingActionButton(
        onClick = { viewModel.useHint() },
        modifier = Modifier.graphicsLayer(scaleX = shownRatio, scaleY = shownRatio),
    ) {
        BadgeBox(
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_hint),
                    contentDescription = null,
                )
            },
            badgeContent = badgeContent(availableCount),
        )
    }
}

private fun badgeContent(availableCount: Loadable<Int>): @Composable (RowScope.() -> Unit)? {
    return (availableCount as? Ready)?.let {
        { Text(it.value.toString()) }
    }
}