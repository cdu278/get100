package tickets.digits

interface TicketDigit {

    val value: Int
}

fun TicketDigit(value: Int): TicketDigit = IntTicketDigit(value)

@JvmInline
private value class IntTicketDigit(
    override val value: Int,
) : TicketDigit {

    init {
        require(value in 0..9) { "$value is not a digit" }
    }
}
