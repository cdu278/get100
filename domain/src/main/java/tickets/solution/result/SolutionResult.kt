package tickets.solution.result

sealed interface SolutionResult {

    object Undefined : SolutionResult

    data class Defined(
            val value: Double,
    ) : SolutionResult
}