package tickets.solution.signs.replacement

import tickets.solution.Solution
import tickets.solution.signs.SolutionSign
import tickets.solution.signs.asIterable

internal fun Solution.replacementsToTransformInto(other: Solution): List<SignReplacement> {
    return this
        .asIterable()
        .zipIndexed(other.asIterable())
        .filter { (actualSign, targetSign) -> actualSign != targetSign }
        .map { (_, newSign, index) -> SignReplacement(position = index, newSign) }
}

private fun Iterable<SolutionSign>.zipIndexed(
    other: Iterable<SolutionSign>,
): Iterable<Triple<SolutionSign, SolutionSign, Int>> {
    var index = 0
    return this.zip(other) { fromThis, fromThat -> Triple(fromThis, fromThat, index++) }
}
