package tickets.solution.gap

data class SolutionGapsState(
    val enabled: Boolean,
    val highlightedPosition: Int,
) {

    companion object {

        val Preview = SolutionGapsState(enabled = true, highlightedPosition = 2)
    }
}
