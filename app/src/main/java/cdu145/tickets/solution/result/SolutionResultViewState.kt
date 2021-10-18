package cdu145.tickets.solution.result

import cdu145.tickets.solution.result.SolutionResultViewState.NotSolved.Sign.*
import cdu145.tickets.solution.result.SolutionResultViewState.NotSolved.Value.Defined
import cdu145.tickets.solution.result.SolutionResultViewState.NotSolved.Value.Undefined

sealed interface SolutionResultViewState {

    object Solved : SolutionResultViewState

    data class NotSolved(
        val sign: Sign,
        val value: Value,
    ) : SolutionResultViewState {

        enum class Sign {

            EqualTo, AlmostEqualTo;

            companion object {

                fun createFor(solutionResult: SolutionResult.Defined): Sign {
                    return solutionResult.value.let {
                        if (it.toInt().toDouble() == it) EqualTo else AlmostEqualTo
                    }
                }
            }
        }

        sealed interface Value {

            object Undefined : Value

            data class Defined(val value: Int) : Value
        }
    }

    companion object {

        fun createFrom(solutionResult: SolutionResult): SolutionResultViewState {
            return when (solutionResult) {
                SolutionResult.Undefined -> {
                    NotSolved(sign = AlmostEqualTo, value = Undefined)
                }
                is SolutionResult.Defined -> {
                    if (solutionResult.isHundred) {
                        Solved
                    } else {
                        NotSolved(
                            sign = NotSolved.Sign.createFor(solutionResult),
                            value = Defined(solutionResult.value.toInt()),
                        )
                    }
                }
            }
        }
    }
}