package tickets.solution.chain

import tickets.digits.TicketDigit
import tickets.expression.Expression
import tickets.expression.NumberExpression

internal class OneDigitSolutionChain(
    private val digit: TicketDigit,
) : SolutionChain {

    override fun expression(): Expression = NumberExpression(digit.value.toDouble())
}