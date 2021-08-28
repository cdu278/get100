package tickets.solution.signs.position

internal class ShiftedPosition(
    private val position: SignPosition,
    private val shift: Int,
) : SignPosition {

    override val value: Int
        get() = position.value + shift

    override fun compareTo(other: SignPosition): Int = value.compareTo(other.value)
}

@Suppress("FunctionName")
internal fun PositionAfter(position: SignPosition) : SignPosition {
    return ShiftedPosition(position, shift = +1)
}