package tickets.solution.signs.position

internal class ShiftedPosition(
    private val position: SignPosition,
    private val shift: Int,
) : SignPosition {

    override val value: Int
        get() = position.value + shift

    override fun compareTo(other: SignPosition): Int = value.compareTo(other.value)
}

internal class PositionAfter(position: SignPosition) :
    SignPosition by ShiftedPosition(position, shift = +1)

internal class PositionBefore(position: SignPosition) :
    SignPosition by ShiftedPosition(position, shift = -1)