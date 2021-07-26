package tickets.solution.chain

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.Matcher
import tickets.solution.result.affectable.SolutionResultMatch
import tickets.solution.result.affectable.SolutionResultMatches
import tickets.solution.result.value.affectable.SolutionResultValueMatches

private class SolutionChainMatcher(
    private val match: SolutionResultMatch,
    private val expectedResult: String,
) : Matcher.Primitive<SolutionChain>() {

    private fun description(result: String): String = "folded to $result"

    override val description: String
        get() = description(expectedResult)

    override fun invoke(actual: SolutionChain): MatchResult {
        return actual
            .expression()
            .asSolutionResult()
            .affect(match)
            .compute(mismatchDescription = ::description)
    }
}

internal fun foldedToUndefined(): Matcher<SolutionChain> {
    return SolutionChainMatcher(
        SolutionResultMatches.Using(
            SolutionResultValueMatches.WithUndefined,
        ),
        expectedResult = "undefined",
    )
}

internal fun foldedToValueEqualTo(value: Double): Matcher<SolutionChain> {
    return SolutionChainMatcher(
        SolutionResultMatches.Using(
            SolutionResultValueMatches.With(value),
        ),
        expectedResult = value.toString(),
    )
}