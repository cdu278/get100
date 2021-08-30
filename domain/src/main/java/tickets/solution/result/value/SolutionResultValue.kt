package tickets.solution.result.value

interface SolutionResultValue {

    interface UsePurpose<out R> {

        fun useUndefinedValue(): R

        fun useValue(value: Double): R
    }

    fun <R> useFor(purpose: UsePurpose<R>): R

    object Undefined : SolutionResultValue {

        override fun <R> useFor(purpose: UsePurpose<R>): R = purpose.useUndefinedValue()
    }

    class Defined(
        private val value: Double,
    ) : SolutionResultValue {

        override fun <R> useFor(purpose: UsePurpose<R>): R = purpose.useValue(value)
    }
}