package tickets.solution.result

import tickets.solution.result.SolutionResult.Defined

val SolutionResult.isHundred: Boolean
    get() = (this as? Defined)?.value == 100.0