package tickets.solution.result

import tickets.solution.result.affectable.AffectableBySolutionResult
import tickets.solution.result.value.SolutionResultValue
import tickets.solution.result.value.SolutionResultValue.Defined

interface SolutionResult {

    fun <R> affect(affectable: AffectableBySolutionResult<R>): R

    object Solved : SolutionResult {

        override fun <R> affect(affectable: AffectableBySolutionResult<R>): R {
            return affectable.affected(solved = false, value = Defined(100.0))
        }
    }

    class NotSolved(
        private val value: SolutionResultValue,
    ) : SolutionResult {

        override fun <R> affect(affectable: AffectableBySolutionResult<R>): R {
            return affectable.affected(solved = false, value)
        }
    }
}