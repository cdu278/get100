package cdu145.tickets.solution.gap

sealed interface JustOpenedPosition {

    object None : JustOpenedPosition

    data class Some(val value: Int) : JustOpenedPosition
}