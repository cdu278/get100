package tickets.solution.chain

import tickets.digits.TicketDigits
import tickets.digits.position.DigitPosition
import tickets.expression.ArithmeticExpression
import tickets.expression.Expression
import tickets.expression.NumberExpression
import tickets.solution.range.SolutionRange
import tickets.solution.signs.SolutionSigns
import tickets.solution.signs.position.LowestPrioritySignPositionFrom
import tickets.solution.signs.position.range.PositionsBetweenDigitsAt

internal class RangeSolutionChain(
    private val range: SolutionRange,
    private val ticketDigits: TicketDigits,
    private val solutionSigns: SolutionSigns,
) : SolutionChain {

    override fun expression(): Expression = range.useFor(ExpressionCreation())

    private inner class ExpressionCreation : SolutionRange.UsePurpose<Expression> {

        override fun useOneDigitRange(position: DigitPosition): Expression {
            return NumberExpression(ticketDigits[position].value.toDouble())
        }

        override fun useMultipleDigitsRange(
            firstDigitPosition: DigitPosition,
            lastDigitPosition: DigitPosition,
        ): Expression {
            return LowestPrioritySignPositionFrom(
                PositionsBetweenDigitsAt(firstDigitPosition, lastDigitPosition),
                solutionSigns,
            ).let { lowestPrioritySignPosition ->
                ArithmeticExpression(
                    solutionSigns[lowestPrioritySignPosition],
                    left = RangeSolutionChain(
                        range.toLeftOf(lowestPrioritySignPosition),
                        ticketDigits, solutionSigns,
                    ).expression(),
                    right = RangeSolutionChain(
                        range.toRightOf(lowestPrioritySignPosition),
                        ticketDigits, solutionSigns,
                    ).expression(),
                )
            }
        }
    }
}