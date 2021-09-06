package tickets.digits

class DigitsKey(
    private val digits: TicketDigits,
) {

    private val list: List<Int> by lazy(LazyThreadSafetyMode.NONE) {
        List(size = 6) { i -> digits[i].value }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DigitsKey

        if (list != other.list) return false

        return true
    }

    override fun hashCode(): Int = list.hashCode()
}