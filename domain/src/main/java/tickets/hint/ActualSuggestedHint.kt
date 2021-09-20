package tickets.hint

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.withContext
import tickets.actual.Actual
import tickets.hint.available.AvailableHints
import tickets.solution.Solution
import tickets.solution.correct.CorrectSolutions
import tickets.solution.signs.replacement.replacementsToTransformInto
import tickets.solution.signs.replacement.totalComplexity

class ActualSuggestedHint(
    private val correctSolutions: Actual<CorrectSolutions>,
    private val solution: Actual.Mutable<Solution>,
    private val almostThereDialog: AlmostThereDialog,
    private val availableHints: AvailableHints,
    private val justOpenedGapChannel: SendChannel<Int>,
) : Actual<Hint> {

    override suspend fun value(): Hint {
        return withContext(Dispatchers.Default) {
            val actualSolution = solution.value()
            correctSolutions.value()
                .map { actualSolution.replacementsToTransformInto(it) }
                .minByOrNull { it.totalComplexity }
                ?.let { replacements ->
                    if (replacements.size == 1) {
                        AlmostThereHint(almostThereDialog)
                    } else {
                        SignReplacingHint(
                            replacement = replacements.random(),
                            solution, availableHints, justOpenedGapChannel,
                        )
                    }
                }
                ?: error("Correct solutions collection is empty")
        }
    }
}