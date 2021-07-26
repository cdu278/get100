package tickets.solution.result.affectable

import tickets.solution.result.value.SolutionResultValue

interface AffectableBySolutionResult<out R> {

    fun affected(solved: Boolean, value: SolutionResultValue): R
}