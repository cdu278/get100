package tickets.expression.evaluation

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.Matcher
import tickets.solution.result.affectable.SolutionResultMatch
import tickets.solution.result.affectable.SolutionResultMatches
import tickets.solution.result.value.affectable.SolutionResultValueMatches

private class EvaluationResultMatcher(
    private val match: SolutionResultMatch,
    private val expectedResult: String,
) : Matcher<ExpressionEvaluation> {

    private fun description(result: String): String = "results $result"

    override val description: String
        get() = description(expectedResult)

    override fun invoke(actual: ExpressionEvaluation): MatchResult {
        return actual
            .evaluate()
            .asSolutionResult()
            .affect(match)
            .compute(mismatchDescription = ::description)
    }
}

internal fun resultsUndefined(): Matcher<ExpressionEvaluation> {
    return EvaluationResultMatcher(
        SolutionResultMatches.Using(
            SolutionResultValueMatches.WithUndefined,
        ),
        expectedResult = "undefined",
    )
}

internal fun resultsValueEqualTo(value: Double): Matcher<ExpressionEvaluation> {
    return EvaluationResultMatcher(
        SolutionResultMatches.Using(
            SolutionResultValueMatches.With(value),
        ),
        expectedResult = value.toString(),
    )
}