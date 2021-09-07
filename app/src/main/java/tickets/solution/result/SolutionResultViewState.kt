package tickets.solution.result

import tickets.solution.result.SolutionResultViewState.Sign.EqualTo
import tickets.solution.result.SolutionResultViewState.Value.Defined
import tickets.solution.result.SolutionResultViewState.Value.Undefined
import tickets.solution.result.value.SolutionResultValue

data class SolutionResultViewState(
    val solved: Boolean,
    val sign: Sign,
    val value: Value,
) {

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
            return SolutionResultViewState(
                solved = true,
                sign = EqualTo,
                value = Defined(100),
            )
        }

        override fun useNotSolved(value: SolutionResultValue): SolutionResultViewState {
            return SolutionResultViewState(
                solved = false,
                sign = value.useFor(Sign.Creation),
                value = value.useFor(Value.Creation),
            )
        }
    }

    companion object {

        val Initial = SolutionResultViewState(
            solved = false,
            sign = EqualTo,
            value = Undefined,
        )
    }
}