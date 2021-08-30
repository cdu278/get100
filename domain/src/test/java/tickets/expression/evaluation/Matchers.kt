package tickets.expression.evaluation

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.Matcher
import tickets.solution.result.MatchingResult
import tickets.solution.result.value.MatchingResultValue

private class EvaluationResultMatcher(
    private val matching: MatchingResult,
    private val expectedResultDescription: String,
) : Matcher<ExpressionEvaluation> {

    private fun description(result: String): String = "results $result"

    override val description: String
        get() = description(expectedResultDescription)

    override fun invoke(actual: ExpressionEvaluation): MatchResult {
        return actual
            .evaluate()
            .asSolutionResult()
            .useFor(matching)
            .compute(mismatchDescription = ::description)
    }
}

internal fun resultsUndefined(): Matcher<ExpressionEvaluation> {
    return EvaluationResultMatcher(
        MatchingResult.WithNotSolved(MatchingResultValue.WithUndefined),
        expectedResultDescription = "undefined",
    )
}

internal fun resultsValueEqualTo(value: Double): Matcher<ExpressionEvaluation> {
    return EvaluationResultMatcher(
        MatchingResult.WithNotSolved(
            MatchingResultValue.WithDefined(value),
        ),
        expectedResultDescription = value.toString(),
    )
}