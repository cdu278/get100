package tickets.solution.result

import tickets.solution.result.value.SolutionResultValue

interface SolutionResult {

    interface UsePurpose<out R> {

        fun useSolved(): R

        fun useNotSolved(value: SolutionResultValue): R
    }

    fun <R> useFor(purpose: UsePurpose<R>): R

    object Solved : SolutionResult {

        override fun <R> useFor(purpose: UsePurpose<R>): R = purpose.useSolved()
    }

    class NotSolved(
        private val value: SolutionResultValue,
    ) : SolutionResult {

        override fun <R> useFor(purpose: UsePurpose<R>): R = purpose.useNotSolved(value)
    }
}