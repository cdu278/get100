package cdu145.tickets.number

interface TicketNumber {

    val value: Int

    companion object {

        val Zero: TicketNumber
            get() = TicketNumber(0)
    }
}

fun TicketNumber(value: Int): TicketNumber = IntTicketNumber(value)

@JvmInline
private value class IntTicketNumber(
    override val value: Int,
) : TicketNumber {

    init {
        require(value in 0..999_999) { "Invalid ticket number: $value" }
    }
}