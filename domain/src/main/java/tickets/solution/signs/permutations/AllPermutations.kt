package tickets.solution.signs.permutations

import tickets.solution.signs.ListSolutionSigns
import tickets.solution.signs.SolutionSign.NONE

val AllSignsPermutations: SolutionsSignsPermutations
    get() = SignPermutationsOfLength(5)
        .filterNot {
            it.all { sign -> sign == NONE }
        }
        .map { ListSolutionSigns(it) }