package tickets.solution.signs.permutations

import tickets.solution.signs.ArithmeticSign
import tickets.solution.signs.ListSolutionSigns

val AllSignsPermutations: SolutionsSignsPermutations
    get() = SignPermutationsOfLength(5)
        .filterNot {
            it.all { sign -> sign.value == ArithmeticSign.NONE }
        }
        .map { ListSolutionSigns(it) }