package tickets.solution.signs.position

import tickets.digits.position.DigitPosition

internal class PositionAfterDigitAt(
    private val digitPosition: DigitPosition,
) : SignPosition {

    override val value: Int
        get() = digitPosition.value

    override fun compareTo(other: SignPosition): Int = value.compareTo(other.value)
}

internal class PositionBeforeDigitAt(
    private val digitPosition: DigitPosition,
) : SignPosition {

    override val value: Int
        get() = digitPosition.value - 1

    override fun compareTo(other: SignPosition): Int = value.compareTo(other.value)
}
