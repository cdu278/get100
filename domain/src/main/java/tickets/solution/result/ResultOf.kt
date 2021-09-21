package tickets.solution.result

import tickets.digits.TicketDigit
import tickets.digits.TicketDigits
import tickets.digits.position.DigitPosition
import tickets.solution.Solution
import tickets.solution.signs.ArithmeticSign.*
import tickets.solution.signs.SolutionSign

internal fun resultOf(solution: Solution, ticketDigits: TicketDigits): SolutionResult {
    return solution.resultAgainst(ticketDigits, from = 0, to = 5)
}

private fun Solution.resultAgainst(
        ticketDigits: TicketDigits,
        from: Int,
        to: Int,
): SolutionResult {
    return if (from == to) {
        singleDigitResult(ticketDigits[DigitPosition(from)])
    } else {
        val range = positionsBetweenDigits(from, to)
        val position = findLastInRange(range) { it == PLUS || it == MINUS }
                ?: findLastInRange(range) { it == TIMES || it == DIV }
                ?: findLastInRange(range) { it == NONE }!!
        return arithmeticOperationResult(
                sign = this[position],
                left = resultAgainst(ticketDigits, from, positionOfDigitBefore(position)),
                right = resultAgainst(ticketDigits, positionOfDigitAfter(position), to),
        )
    }
}

private fun singleDigitResult(digit: TicketDigit): SolutionResult {
    return SolutionResult.Defined(digit.value.toDouble())
}

private fun positionsBetweenDigits(firstDigitPosition: Int, lastDigitPosition: Int): IntRange {
    return IntRange(firstDigitPosition, lastDigitPosition - 1)
}

private inline fun Solution.findLastInRange(
        range: IntRange,
        predicate: (SolutionSign) -> Boolean,
): Int? {
    var positionOfLast = -1
    for (position in range) {
        if (predicate(this[position])) {
            positionOfLast = position
        }
    }
    return positionOfLast.takeIf { it != -1 }
}

private fun positionOfDigitBefore(signPosition: Int): Int = signPosition

private fun positionOfDigitAfter(signPosition: Int): Int = signPosition + 1