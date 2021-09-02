package tickets.solution.result

data class SolutionResultViewState(
    val ready: Boolean,
    val value: Value,
) {

    data class Value(
        val sign: Sign,
        val value: Int,
    ) {

        sealed interface Sign {

            object EqualTo : Sign

            object AlmostEqualTo : Sign
        }
    }

    companion object {

        val Preview = SolutionResultViewState(
            ready = true,
            Value(Value.Sign.AlmostEqualTo, 100),
        )
    }
}