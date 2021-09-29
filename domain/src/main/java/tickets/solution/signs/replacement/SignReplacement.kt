package tickets.solution.signs.replacement

import tickets.solution.signs.SolutionSign
import tickets.solution.signs.SolutionSign.*

internal data class SignReplacement(
    val position: Int,
    val newSign: SolutionSign,
) {
    val complexity: Int
        get() = when (newSign) {
            PLUS -> 1
            MINUS -> 1
            TIMES -> 2
            DIV -> 3
            NONE -> 0
        }
}