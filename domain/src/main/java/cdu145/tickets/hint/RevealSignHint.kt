package cdu145.tickets.hint

import cdu145.actual.Actual
import cdu145.tickets.hint.available.AvailableHints
import kotlinx.coroutines.channels.SendChannel
import cdu145.tickets.solution.Solution
import cdu145.tickets.solution.signs.AlteredSolutionSigns
import cdu145.tickets.solution.signs.replacement.SignReplacement

internal class RevealSignHint(
    private val replacement: SignReplacement,
    private val solution: Actual.Mutable<Solution>,
    private val availableHints: AvailableHints,
    private val justRevealedGapChannel: SendChannel<Int>,
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
            justRevealedGapChannel.send(replacement.position)
        }
    }
}