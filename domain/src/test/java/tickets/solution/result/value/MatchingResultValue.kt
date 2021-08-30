package tickets.solution.result.value

import tickets.match.ComputableMatchResult
import tickets.match.ComputableToMatch
import tickets.match.ComputableToMismatch

internal interface MatchingResultValue : SolutionResultValue.UsePurpose<ComputableMatchResult> {

    object WithUndefined : MatchingResultValue {

        override fun useUndefinedValue(): ComputableMatchResult = ComputableToMatch

        override fun useValue(value: Double): ComputableMatchResult {
            return ComputableToMismatch(actualResult = "defined (value)")
        }
    }

    class WithDefined(
        private val expectedValue: Double,
    ) : MatchingResultValue {

        override fun useUndefinedValue(): ComputableMatchResult {
            return ComputableToMismatch(actualResult = "undefined")
        }

        override fun useValue(value: Double): ComputableMatchResult {
            return if (value == expectedValue) {
                ComputableToMatch
            } else {
                ComputableToMismatch(actualResult = "$value")
            }
        }
    }
}