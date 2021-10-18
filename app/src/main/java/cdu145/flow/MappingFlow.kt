package cdu145.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("FunctionName")
fun <T, R> MappingFlow(
    flow: Flow<T>,
    transform: suspend (T) -> R,
): Flow<R> {
    return flow.map(transform)
}