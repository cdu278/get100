package tickets.hint.available

import tickets.actual.Actual

interface AvailableHints {

    suspend fun acquire(block: suspend () -> Unit)
}

fun AvailableHints(
    count: Actual.Mutable<Int>,
    hintRestoration: HintRestoration,
): AvailableHints {
    return object : AvailableHints {

        override suspend fun acquire(block: suspend () -> Unit) {
            count.mutate {
                check(it > 0) { "No hints available" }
                block.invoke()
                hintRestoration.schedule()
                it - 1
            }
        }
    }
}