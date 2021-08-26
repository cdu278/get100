package tickets.flowable

import kotlinx.coroutines.flow.Flow

internal fun <T> Flow<T>.asFlowable() = object : Flowable<T> {

    override val flow: Flow<T>
        get() = this@asFlowable
}