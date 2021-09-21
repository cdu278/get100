package tickets.solution.result

import tickets.solution.result.SolutionResult.Defined
import tickets.solution.result.SolutionResult.Undefined
import tickets.solution.result.SolutionResultViewState.Ready.Sign.*
import tickets.solution.result.SolutionResultViewState.Ready.Value

sealed interface SolutionResultViewState {

    companion object {

        fun createFrom(solutionResult: SolutionResult): SolutionResultViewState {
            return when (solutionResult) {
                is Undefined -> Ready(solved = false, sign = EqualTo, value = Value.Undefined)
                is Defined -> Ready(
                        solved = solutionResult.isHundred,
                        sign = Ready.Sign.createFor(solutionResult),
                        value = Value.createFor(solutionResult),
                )
            }
        }
    }

    object NotReady : SolutionResultViewState

    data class Ready(
        val solved: Boolean,
        val sign: Sign,
        val value: Value,
    ) : SolutionResultViewState {

        enum class Sign {

            EqualTo, AlmostEqualTo;

            companion object {

                fun createFor(solutionResult: SolutionResult): Sign {
                    return when (solutionResult) {
                        is Undefined -> EqualTo
                        is Defined -> {
                            val value = solutionResult.value
                            if (value.toInt().toDouble() == value) {
                                EqualTo
                            } else {
                                AlmostEqualTo
                            }
                        }
                    }
                }
            }
        }

        sealed interface Value {

            companion object {

                fun createFor(solutionResult: SolutionResult): Value {
                    return when (solutionResult) {
                        is SolutionResult.Undefined -> Undefined
                        is SolutionResult.Defined -> Defined(solutionResult.value.toInt())
                    }
                }
            }

            object Undefined : Value

            data class Defined(
                val value: Int,
            ) : Value
        }
    }
}