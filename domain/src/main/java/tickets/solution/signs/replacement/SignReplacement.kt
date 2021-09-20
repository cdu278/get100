package tickets.solution.signs.replacement

import tickets.solution.Solution
import tickets.solution.signs.AlteredSolutionSigns
import tickets.solution.signs.ArithmeticSign.*
import tickets.solution.signs.SolutionSign
import tickets.solution.signs.position.SignPosition

internal interface SignReplacement {

    val complexity: Int

    fun applyTo(solution: Solution): Solution
}

internal fun SignReplacement(
    position: Int,
    newSign: SolutionSign,
): SignReplacement {
    return object : SignReplacement {

        override val complexity: Int
            get() = when (newSign.value) {
                PLUS -> 1
                MINUS -> 1
                TIMES -> 2
                DIV -> 3
                NONE -> 0
            }

        override fun applyTo(solution: Solution): Solution {
            return AlteredSolutionSigns(
                original = solution,
                targetPosition = SignPosition(position),
                newSign,
            )
        }
    }
}