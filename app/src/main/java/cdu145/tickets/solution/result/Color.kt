package cdu145.tickets.solution.result

import cdu145.tickets.solution.result.SolutionResultViewState.NotSolved
import cdu145.tickets.solution.result.SolutionResultViewState.Solved

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