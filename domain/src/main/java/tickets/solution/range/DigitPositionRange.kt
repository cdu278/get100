package tickets.solution.range

import tickets.digits.position.DigitPosition
import tickets.digits.position.PositionAfterSignAt
import tickets.digits.position.PositionBeforeSignAt
import tickets.solution.signs.position.SignPosition

internal class DigitPositionRange(
    private val from: DigitPosition,
    private val to: DigitPosition,
) : SolutionRange {

    private val closedRange: ClosedRange<DigitPosition>
        get() = from..to

    init {
        require(from <= to) { "Cannot create range [$closedRange]" }
    }

    override fun <R> useFor(purpose: SolutionRange.UsePurpose<R>): R {
        return if (from < to) {
            purpose.useMultipleDigitsRange(from, to)
        } else {
            purpose.useOneDigitRange(position = from)
        }
    }

    private fun SignPosition.ensureInRange(): SignPosition {
        this.value.let {
            require(it >= from.value && it < to.value) {
                "Sign position $it is not in digit position range [$closedRange]"
            }
        }
        return this
    }

    override fun toRightOf(signPosition: SignPosition): SolutionRange {
        return DigitPositionRange(
            from = PositionAfterSignAt(signPosition.ensureInRange()),
            to,
        )
    }

    override fun toLeftOf(signPosition: SignPosition): SolutionRange {
        return DigitPositionRange(
            from,
            to = PositionBeforeSignAt(signPosition.ensureInRange()),
        )
    }
}