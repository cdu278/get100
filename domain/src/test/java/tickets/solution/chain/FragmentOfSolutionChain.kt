package tickets.solution.chain

import tickets.digits.IntTicketDigit
import tickets.digits.FragmentOfTicketDigits
import tickets.solution.signs.FragmentOfSolutionSigns
import tickets.solution.signs.SolutionSign
import tickets.solution.signs.position.IntSignPosition
import tickets.solution.signs.position.SignPosition

internal class FragmentOfSolutionChain private constructor(
    digitValues: List<Int>,
    signs: Array<SolutionSign>,
) : SolutionChain by RangeSolutionChain(
    from = SignPosition.FIRST, to = IntSignPosition(signs.size - 1),
    FragmentOfTicketDigits(digitValues.map(::IntTicketDigit)),
    FragmentOfSolutionSigns(*signs),
) {

    constructor(d1: Int, s1: SolutionSign, d2: Int) : this(listOf(d1, d2), arrayOf(s1))

    constructor(d1: Int, s1: SolutionSign, d2: Int, s2: SolutionSign, d3: Int)
            : this(listOf(d1, d2, d3), arrayOf(s1, s2))
}