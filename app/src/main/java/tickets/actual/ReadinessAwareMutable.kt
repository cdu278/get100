package tickets.actual

import kotlinx.coroutines.flow.MutableStateFlow

class ReadinessAwareMutable<T>(
    private val mutable: Actual.Mutable<T>,
    private val readyFlow: MutableStateFlow<Boolean>,
) : Actual.Mutable<T> {

    override suspend fun value(): T = mutable.value()

    override suspend fun mutate(newValue: T) {
        readyFlow.value = false
        mutable.mutate(newValue)
        readyFlow.value = true
    }
}