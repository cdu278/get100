package tickets.digits.position

interface DigitPosition : Comparable<DigitPosition> {

    val value: Int

    companion object {

        val FIRST: DigitPosition
            get() = IntDigitPosition(0)
        val LAST: DigitPosition
            get() = IntDigitPosition(5)
    }
}

fun DigitPosition(value: Int): DigitPosition = IntDigitPosition(value)

@JvmInline
private value class IntDigitPosition(
    override val value: Int,
) : DigitPosition {

    init {
        require(value in 0..5) { "Invalid digit position: $value" }
    }

    override fun compareTo(other: DigitPosition): Int = value.compareTo(other.value)
}