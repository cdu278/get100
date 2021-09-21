package tickets.solution

import tickets.solution.signs.ListSolutionSigns
import tickets.solution.signs.SolutionSign

@Suppress("TestFunctionName")
internal fun Solution(
        s1: SolutionSign,
        s2: SolutionSign,
        s3: SolutionSign,
        s4: SolutionSign,
        s5: SolutionSign,
): Solution {
    return ListSolutionSigns(listOf(s1, s2, s3, s4, s5))
}