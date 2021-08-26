package tickets.flowable

import kotlinx.coroutines.flow.Flow

interface Flowable<out T> {

    val flow: Flow<T>
}