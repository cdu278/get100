package cdu145.tickets.solution.gaps

sealed interface GapPosition {

    object None : GapPosition

    data class Some(val value: Int) : GapPosition
}