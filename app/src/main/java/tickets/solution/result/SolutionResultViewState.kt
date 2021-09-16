package tickets.solution.result

import tickets.solution.result.value.SolutionResultValue

sealed interface SolutionResultViewState {

    object NotReady : SolutionResultViewState

    data class Ready(
        val solved: Boolean,
        val sign: Sign,
        val value: Value,
    ) : SolutionResultViewState {

        enum class Sign {

            EqualTo, AlmostEqualTo;

            object Creation : SolutionResultValue.UsePurpose<Sign> {

                override fun useUndefinedValue(): Sign = EqualTo

                override fun useValue(value: Double): Sign {
                    return if (value.toInt().toDouble() == value) {
                        EqualTo
                    } else {
                        AlmostEqualTo
                    }
                }
            }
        }

        sealed interface Value {

            object Undefined : Value

            data class Defined(
                val value: Int,
            ) : Value

            object Creation : SolutionResultValue.UsePurpose<Value> {

                override fun useUndefinedValue(): Value = Undefined

                override fun useValue(value: Double): Value = Defined(value.toInt())
            }
        }

        object Creation : SolutionResult.UsePurpose<SolutionResultViewState> {

            override fun useSolved(): SolutionResultViewState {
                return Ready(
                    solved = true,
                    sign = Sign.EqualTo,
                    value = Value.Defined(100),
                )
            }

            override fun useNotSolved(value: SolutionResultValue): SolutionResultViewState {
                return Ready(
                    solved = false,
                    sign = value.useFor(Sign.Creation),
                    value = value.useFor(Value.Creation),
                )
            }
        }
    }
}