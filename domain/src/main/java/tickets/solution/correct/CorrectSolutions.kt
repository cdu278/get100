package tickets.solution.correct

import tickets.digits.DigitsOf
import tickets.number.TicketNumber
import tickets.solution.chain.FullSolutionChain
import tickets.solution.result.SolutionResult
import tickets.solution.result.value.SolutionResultValue
import tickets.solution.signs.SolutionSigns
import tickets.solution.signs.permutations.SolutionsSignsPermutations

typealias CorrectSolutions = Iterable<SolutionSigns>

fun TicketNumber.correctSolutions(
    signsPermutations: SolutionsSignsPermutations,
): CorrectSolutions {
    val digits = DigitsOf(this)
    return signsPermutations
        .filter { signsPermutation ->
            FullSolutionChain(digits, solutionSigns = signsPermutation)
                .expression()
                .asSolutionResult()
                .useFor(CheckingCorrectness)
        }
        .toList()
}

private object CheckingCorrectness : SolutionResult.UsePurpose<Boolean> {

    override fun useSolved(): Boolean = true

    override fun useNotSolved(value: SolutionResultValue): Boolean = false
}