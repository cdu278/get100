package tickets.solution.gap

sealed interface JustOpenedPosition {

    object None : JustOpenedPosition

    data class Some(val value: Int) : JustOpenedPosition
}