package cdu145.tickets.hint.available

interface AvailableHints {

    suspend fun acquire(block: suspend () -> Unit)

    companion object {

        const val MaxCount = 5
    }
}