package tickets.solution.result.value

import tickets.solution.result.value.affectable.AffectableBySolutionResultValue

interface SolutionResultValue {

    fun <R> affect(affectable: AffectableBySolutionResultValue<R>): R

    object Undefined : SolutionResultValue {

        override fun <R> affect(
            affectable: AffectableBySolutionResultValue<R>,
        ): R {
            return affectable.affectedByUndefined()
        }
    }

    class Defined(
        private val value: Double,
    ) : SolutionResultValue {

        override fun <R> affect(
            affectable: AffectableBySolutionResultValue<R>,
        ): R {
            return affectable.affectedByDefined(value)
        }
    }
}