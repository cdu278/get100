package cdu145.tickets.hint

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.withContext
import cdu145.actual.Actual
import cdu145.tickets.hint.available.AvailableHints
import cdu145.tickets.solution.Solution
import cdu145.tickets.solution.correct.CorrectSolutions
import cdu145.tickets.solution.signs.replacement.replacementsToTransformInto
import cdu145.tickets.solution.signs.replacement.totalComplexity

class ActualSuggestedHint(
    private val correctSolutions: Actual<CorrectSolutions>,
    private val solution: Actual.Mutable<Solution>,
    private val almostCompletedDialog: AlmostCompletedDialog,
    private val availableHints: AvailableHints,
    private val justRevealedGapChannel: SendChannel<Int>,
) : Actual<Hint> {

    override suspend fun value(): Hint {
        return withContext(Dispatchers.Default) {
            val actualSolution = solution.value()
            correctSolutions.value()
                .map { actualSolution.replacementsToTransformInto(it) }
                .minByOrNull { it.totalComplexity }
                ?.let { replacements ->
                    if (replacements.size == 1) {
                        SolutionAlmostCompletedHint(almostCompletedDialog)
                    } else {
                        RevealSignHint(
                            replacement = replacements.random(),
                            solution, availableHints, justRevealedGapChannel,
                        )
                    }
                }
                ?: error("Correct solutions collection is empty")
        }
    }
}