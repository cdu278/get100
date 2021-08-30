package tickets.solution.result

import tickets.match.ComputableMatchResult
import tickets.match.ComputableToMatch
import tickets.match.ComputableToMismatch
import tickets.solution.result.value.MatchingResultValue
import tickets.solution.result.value.SolutionResultValue
import tickets.solution.result.value.StringFormatting

internal interface MatchingResult : SolutionResult.UsePurpose<ComputableMatchResult> {

    object WithSolved : MatchingResult {

        override fun useSolved(): ComputableMatchResult = ComputableToMatch

        override fun useNotSolved(value: SolutionResultValue): ComputableMatchResult {
            return ComputableToMismatch(
                actualResult = value.useFor(
                    StringFormatting("not solved (%s)"),
                ),
            )
        }
    }

    class WithNotSolved(
        private val matching: MatchingResultValue,
    ) : MatchingResult {

        override fun useSolved(): ComputableMatchResult {
            return ComputableToMismatch(actualResult = "solved")
        }

        override fun useNotSolved(value: SolutionResultValue): ComputableMatchResult {
            return value.useFor(matching)
        }
    }
}