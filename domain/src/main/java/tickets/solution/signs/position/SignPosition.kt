package tickets.solution.signs.position

interface SignPosition : Comparable<SignPosition> {

    val value: Int

    companion object {

        val First: SignPosition
            get() = SignPosition(0)
    }
}

fun SignPosition(value: Int): SignPosition = IntSignPosition(value)

@JvmInline
private value class IntSignPosition(
    override val value: Int,
) : SignPosition {

    init {
        require(value in 0..4) { "Invalid sign position value: $value" }
    }

    override fun compareTo(other: SignPosition): Int = value.compareTo(other.value)
}