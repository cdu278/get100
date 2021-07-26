package tickets.solution.chain

import tickets.digits.TicketDigits
import tickets.digits.position.PositionAfterSignAt
import tickets.digits.position.PositionBeforeSignAt
import tickets.expression.Expression
import tickets.expression.ArithmeticExpression
import tickets.solution.signs.SolutionSigns
import tickets.solution.signs.position.LowestPrioritySignPosition
import tickets.solution.signs.position.PositionAfter
import tickets.solution.signs.position.PositionBefore
import tickets.solution.signs.position.SignPosition

internal class RangeSolutionChain(
    private val from: SignPosition,
    private val to: SignPosition,
    private val ticketDigits: TicketDigits,
    private val solutionSigns: SolutionSigns,
) : SolutionChain {

    override fun expression(): Expression {
        return if (from < to) {
            val signPosition = LowestPrioritySignPosition(solutionSigns, from, to)
            ArithmeticExpression(
                solutionSigns[signPosition],
                left = RangeSolutionChain(
                    from,
                    to = PositionBefore(signPosition),
                    ticketDigits, solutionSigns,
                ).expression(),
                right = RangeSolutionChain(
                    from = PositionAfter(signPosition),
                    to,
                    ticketDigits, solutionSigns,
                ).expression(),
            )
        } else {
            val signPosition = from
            ArithmeticExpression(
                solutionSigns[signPosition],
                left = OneDigitSolutionChain(
                    ticketDigits[PositionBeforeSignAt(signPosition)]
                ).expression(),
                right = OneDigitSolutionChain(
                    ticketDigits[PositionAfterSignAt(signPosition)]
                ).expression(),
            )
        }
    }
}