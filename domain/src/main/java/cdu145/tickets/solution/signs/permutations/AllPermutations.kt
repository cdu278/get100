package cdu145.tickets.solution.signs.permutations

import cdu145.tickets.solution.signs.ListSolutionSigns
import cdu145.tickets.solution.signs.SolutionSign.NONE

val AllSignsPermutations: SolutionsSignsPermutations
    get() = SignPermutationsOfLength(5)
        .filterNot {
            it.all { sign -> sign == NONE }
        }
        .map { ListSolutionSigns(it) }