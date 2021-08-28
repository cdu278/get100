package tickets.solution.chain

import tickets.digits.FragmentOfTicketDigits
import tickets.digits.TicketDigit
import tickets.digits.position.DigitPosition
import tickets.solution.range.DigitPositionRange
import tickets.solution.signs.FragmentOfSolutionSigns
import tickets.solution.signs.SolutionSign

internal class FragmentOfSolutionChain private constructor(
    digitValues: List<Int>,
    signs: Array<SolutionSign>,
) : SolutionChain by RangeSolutionChain(
    DigitPositionRange(DigitPosition.FIRST, DigitPosition(signs.size)),
    FragmentOfTicketDigits(digitValues.map(::TicketDigit)),
    FragmentOfSolutionSigns(*signs),
) {

    constructor(d1: Int, s1: SolutionSign, d2: Int) : this(listOf(d1, d2), arrayOf(s1))

    constructor(d1: Int, s1: SolutionSign, d2: Int, s2: SolutionSign, d3: Int)
            : this(listOf(d1, d2, d3), arrayOf(s1, s2))
}