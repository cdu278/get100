package tickets.ui

import androidx.compose.animation.core.AnimationConstants
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

private fun animationStartFlow(animationDuration: Int): Flow<Unit> {
    return flow {
        while (true) {
            emit(Unit)
            delay(animationDuration.toLong())
        }
    }
}

fun <State> Flow<State>.animationAware(
    animationDuration: Int = AnimationConstants.DefaultDurationMillis,
): Flow<State> {
    return this.zip(animationStartFlow(animationDuration)) { state, _ -> state }
}