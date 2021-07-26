package tickets.solution.result.affectable

import tickets.match.ComputableMatchResult
import tickets.solution.result.value.SolutionResultValue
import tickets.solution.result.value.affectable.SolutionResultValueMatch

internal typealias SolutionResultMatch = AffectableBySolutionResult<ComputableMatchResult>

internal class SolutionResultMatches private constructor() {

    class Using(
        private val valueMatch: SolutionResultValueMatch,
    ) : SolutionResultMatch {

        override fun affected(solved: Boolean, value: SolutionResultValue): ComputableMatchResult {
            return value.affect(valueMatch)
        }
    }
}