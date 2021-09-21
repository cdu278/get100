package tickets.solution.signs.replacement

import tickets.solution.signs.SolutionSign
import tickets.solution.signs.SolutionSign.*

internal interface SignReplacement {

    val complexity: Int

    interface UsePurpose<out R> {

        fun use(position: Int, newSign: SolutionSign): R
    }

    fun <R> useFor(purpose: UsePurpose<R>): R
}

internal fun SignReplacement(
    position: Int,
    newSign: SolutionSign,
): SignReplacement {
    return object : SignReplacement {

        override val complexity: Int
            get() = when (newSign) {
                PLUS -> 1
                MINUS -> 1
                TIMES -> 2
                DIV -> 3
                NONE -> 0
            }

        override fun <R> useFor(purpose: SignReplacement.UsePurpose<R>): R {
            return purpose.use(position, newSign)
        }
    }
}