package tickets.hint

import tickets.actual.Actual
import tickets.hint.available.AvailableHints
import tickets.solution.Solution
import tickets.solution.signs.replacement.SignReplacement

internal class SignReplacingHint(
    private val replacement: SignReplacement,
    private val solution: Actual.Mutable<Solution>,
    private val availableHints: AvailableHints,
) : Hint {

    override suspend fun use() {
        availableHints.acquire {
            solution.mutate { replacement.applyTo(it) }
        }
    }
}