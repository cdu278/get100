package tickets.solution.gap

import tickets.solution.Solution

sealed interface ShownSolutionState {

    object NotReady : ShownSolutionState

    data class Ready(val solution: Solution) : ShownSolutionState
}