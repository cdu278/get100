package tickets.solution.result.value.affectable

import tickets.match.ComputableMatchResult
import tickets.match.ComputableToMatch
import tickets.match.ComputableToMismatch

internal typealias SolutionResultValueMatch = AffectableBySolutionResultValue<ComputableMatchResult>

internal class SolutionResultValueMatches private constructor() {

    object WithUndefined : SolutionResultValueMatch {

        override fun affectedByUndefined(): ComputableMatchResult = ComputableToMatch

        override fun affectedByDefined(value: Double): ComputableMatchResult {
            return ComputableToMismatch(actualResult = value.toString())
        }
    }

    class With(
        private val expected: Double,
    ) : SolutionResultValueMatch {

        override fun affectedByUndefined(): ComputableMatchResult {
            return ComputableToMismatch(actualResult = "undefined")
        }

        override fun affectedByDefined(value: Double): ComputableMatchResult {
            return if (value == expected) {
                ComputableToMatch
            } else {
                ComputableToMismatch(actualResult = value.toString())
            }
        }
    }
}