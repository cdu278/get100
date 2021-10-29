package cdu145.tickets.solution.sign.replacement

import cdu145.tickets.solution.sign.SolutionSign
import cdu145.tickets.solution.sign.SolutionSign.*

internal data class SignReplacement(
    val position: Int,
    val newSign: SolutionSign,
) {
    val complexity: Int
        get() = when (newSign) {
            Plus -> 1
            Minus -> 1
            Times -> 2
            Div -> 3
            None -> 0
        }
}