package tickets.hint

import kotlinx.coroutines.channels.SendChannel
import tickets.actual.Actual
import tickets.hint.available.AvailableHints
import tickets.solution.Solution
import tickets.solution.signs.AlteredSolutionSigns
import tickets.solution.signs.replacement.SignReplacement

internal class SignReplacingHint(
    private val replacement: SignReplacement,
    private val solution: Actual.Mutable<Solution>,
    private val availableHints: AvailableHints,
    private val justOpenedGapChannel: SendChannel<Int>,
) : Hint {

    override suspend fun use() {
        availableHints.acquire {
            solution.mutate {
                AlteredSolutionSigns(
                    original = it,
                    targetPosition = replacement.position,
                    newSign = replacement.newSign,
                )
            }
            justOpenedGapChannel.send(replacement.position)
        }
    }
}