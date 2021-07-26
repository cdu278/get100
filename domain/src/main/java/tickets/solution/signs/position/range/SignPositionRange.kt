package tickets.solution.signs.position.range

import tickets.solution.signs.position.PositionAfter
import tickets.solution.signs.position.SignPosition

internal class SignPositionRange(
    private val from: SignPosition,
    private val to: SignPosition,
) : Iterable<SignPosition> {

    init {
        require(from <= to) { "Cannot create empty range" }
    }

    override fun iterator(): Iterator<SignPosition> {
        return object : Iterator<SignPosition> {

            var current = from

            override fun hasNext(): Boolean = current <= to

            override fun next(): SignPosition {
                return current.also { current = PositionAfter(it) }
            }
        }
    }
}