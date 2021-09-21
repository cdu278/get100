package tickets.hint

import kotlinx.coroutines.channels.SendChannel
import tickets.actual.Actual
import tickets.hint.available.AvailableHints
import tickets.solution.Solution
import tickets.solution.signs.AlteredSolutionSigns
import tickets.solution.signs.SolutionSign
import tickets.solution.signs.replacement.SignReplacement

internal class SignReplacingHint(
    private val replacement: SignReplacement,
    private val solution: Actual.Mutable<Solution>,
    private val availableHints: AvailableHints,
    private val justOpenedGapChannel: SendChannel<Int>,
) : Hint {

    override suspend fun use() {
        availableHints.acquire {
            solution.mutate { replacement.useFor(ReplacingIn(it)) }
            justOpenedGapChannel.send(replacement.useFor(OpenedGapCreation))
        }
    }

    private class ReplacingIn(
        private val solution: Solution,
    ) : SignReplacement.UsePurpose<Solution> {

        override fun use(position: Int, newSign: SolutionSign): Solution {
            return AlteredSolutionSigns(
                original = solution,
                targetPosition = position,
                newSign,
            )
        }
    }

    private object OpenedGapCreation : SignReplacement.UsePurpose<Int> {

        override fun use(position: Int, newSign: SolutionSign): Int = position
    }
}