package tickets.digits.position

import tickets.solution.signs.position.SignPosition

internal class PositionBeforeSignAt(
    private val signPosition: SignPosition,
) : DigitPosition {

    override val value: Int
        get() = signPosition.value
}

internal class PositionAfterSignAt(
    private val signPosition: SignPosition,
) : DigitPosition {

    override val value: Int
        get() = signPosition.value + 1
}