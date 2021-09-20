package tickets.hint

sealed interface AvailableCountState {

    object NotReady : AvailableCountState

    data class Ready(val value: Int) : AvailableCountState
}