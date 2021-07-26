package tickets.solution.signs.priority

import tickets.solution.signs.ArithmeticSign
import tickets.solution.signs.SolutionSign

internal class PriorityOf(
    private val sign: SolutionSign,
) : SignPriority {

    override val value: Int
        get() = when (sign.value) {
            ArithmeticSign.PLUS, ArithmeticSign.MINUS -> 0
            ArithmeticSign.TIMES, ArithmeticSign.DIV -> 1
            ArithmeticSign.NONE -> 2
        }

    override fun compareTo(other: SignPriority): Int = value.compareTo(other.value)
}