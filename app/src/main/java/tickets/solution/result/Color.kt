package tickets.solution.result

import tickets.solution.result.SolutionResultViewState.NotSolved
import tickets.solution.result.SolutionResultViewState.Solved

enum class ResultTextColor {

    WhenNotSolved,  WhenSolved;

    companion object {

        fun dependingOn(state: SolutionResultViewState): ResultTextColor {
            return when (state) {
                is NotSolved -> WhenNotSolved
                is Solved -> WhenSolved
            }
        }
    }
}