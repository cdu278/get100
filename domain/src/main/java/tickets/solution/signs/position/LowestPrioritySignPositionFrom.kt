package tickets.solution.signs.position

import tickets.solution.signs.SolutionSigns
import tickets.solution.signs.priority.PriorityOf

internal class LowestPrioritySignPositionFrom(
    private val positions: Iterable<SignPosition>,
    private val signs: SolutionSigns,
) : SignPosition {

    private val delegate: SignPosition
        get() {
            return positions.iterator().run {
                var minPriorityPosition = next()
                var minPriority = PriorityOf(signs[minPriorityPosition])
                while (hasNext()) {
                    val position = next()
                    val priority = PriorityOf(signs[position])
                    if (priority <= minPriority) {
                        minPriority = priority
                        minPriorityPosition = position
                    }
                }
                minPriorityPosition
            }
        }

    override val value: Int
        get() = delegate.value

    override fun compareTo(other: SignPosition): Int = delegate.compareTo(other)
}