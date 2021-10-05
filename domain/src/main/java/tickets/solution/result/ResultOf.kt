package tickets.solution.result

import tickets.digits.TicketDigits
import tickets.solution.Solution
import tickets.solution.result.SolutionResult.Undefined
import tickets.solution.result.SolutionResult.Defined
import tickets.solution.signs.SolutionSign
import tickets.solution.signs.SolutionSign.*
import tickets.util.SignPositionRangePool

internal fun resultOf(solution: Solution, ticketDigits: TicketDigits): SolutionResult {
    return solution
        .resultInRangeOrNull(ticketDigits, from = 0, to = 5)
        ?.let(::Defined)
        ?: Undefined
}

private fun Solution.resultInRangeOrNull(
        ticketDigits: TicketDigits,
        from: Int,
        to: Int,
): Double? {
    return if (from == to) {
        ticketDigits[from].toDouble()
    } else {
        val range = positionsBetweenDigits(from, to)
        val position = findLastInRange(range) { it == PLUS || it == MINUS }
                ?: findLastInRange(range) { it == TIMES || it == DIV }
                ?: findLastInRange(range) { it == NONE }!!
        return arithmeticOperationResultOrNull(
                sign = this[position],
                left = resultInRangeOrNull(ticketDigits, from, positionOfDigitBefore(position)),
                right = resultInRangeOrNull(ticketDigits, positionOfDigitAfter(position), to),
        )
    }
}

private fun positionsBetweenDigits(firstDigitPosition: Int, lastDigitPosition: Int): IntRange {
    return SignPositionRangePool.obtain(start = firstDigitPosition, end = lastDigitPosition - 1)
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