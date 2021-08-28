package tickets.solution.signs.position.range

import tickets.digits.position.DigitPosition
import tickets.solution.signs.position.PositionAfter
import tickets.solution.signs.position.PositionAfterDigitAt
import tickets.solution.signs.position.PositionBeforeDigitAt
import tickets.solution.signs.position.SignPosition
import tickets.util.RangeIterator

internal class PositionsBetweenDigitsAt(
    private val firstPosition: DigitPosition,
    private val lastPosition: DigitPosition,
) : Iterable<SignPosition> {

    init {
        require(firstPosition < lastPosition) {
            "There are no signs between digits at ${firstPosition.value} and ${lastPosition.value}"
        }
    }

    override fun iterator(): Iterator<SignPosition> {
        return RangeIterator(
            from = PositionAfterDigitAt(firstPosition),
            to = PositionBeforeDigitAt(lastPosition),
            valueAfter = ::PositionAfter,
        )
    }
}