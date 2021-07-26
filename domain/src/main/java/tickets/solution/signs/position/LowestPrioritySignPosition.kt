package tickets.solution.signs.position

import tickets.solution.signs.SolutionSigns
import tickets.solution.signs.position.range.SignPositionRange
import tickets.solution.signs.priority.PriorityOf

internal class LowestPrioritySignPosition(
    private val signs: SolutionSigns,
    private val from: SignPosition,
    private val to: SignPosition,
) : SignPosition {

    private val delegate: SignPosition
        get() = SignPositionRange(from, to).minByOrNull { PriorityOf(signs[it]) }!!

    override val value: Int
        get() = delegate.value

    override fun compareTo(other: SignPosition): Int = delegate.compareTo(other)
}