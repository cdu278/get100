package tickets.solution.signs

@Suppress("TestFunctionName")
internal fun SolutionSigns(vararg signs: SolutionSign): SolutionSigns {
    return ListSolutionSigns(signs.toList())
}