package tickets.solution.chain

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.Matcher
import tickets.solution.result.MatchingResult
import tickets.solution.result.value.MatchingResultValue

private class SolutionChainMatcher(
    private val matching: MatchingResult,
    private val expectedResultDescription: String,
) : Matcher.Primitive<SolutionChain>() {

    private fun description(result: String): String = "folded to $result"

    override val description: String
        get() = description(expectedResultDescription)

    override fun invoke(actual: SolutionChain): MatchResult {
        return actual
            .expression()
            .asSolutionResult()
            .useFor(matching)
            .compute(mismatchDescription = ::description)
    }
}

internal fun foldedToUndefined(): Matcher<SolutionChain> {
    return SolutionChainMatcher(
        MatchingResult.WithNotSolved(MatchingResultValue.WithUndefined),
        expectedResultDescription = "undefined",
    )
}

internal fun foldedToValueEqualTo(value: Double): Matcher<SolutionChain> {
    return SolutionChainMatcher(
        MatchingResult.WithNotSolved(
            MatchingResultValue.WithDefined(value),
        ),
        expectedResultDescription = value.toString(),
    )
}