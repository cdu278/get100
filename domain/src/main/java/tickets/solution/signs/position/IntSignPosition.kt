package tickets.solution.signs.position

@JvmInline
value class IntSignPosition(
    override val value: Int,
) : SignPosition {

    init {
        require(value in 0..4) { "Invalid sign position value: $value" }
    }

    override fun compareTo(other: SignPosition): Int = value.compareTo(other.value)
}