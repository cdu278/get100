package tickets.digits

@JvmInline
value class IntTicketDigit(
    override val value: Int,
) : TicketDigit {

    init {
        require(value in 0..9) { "$value is not a digit" }
    }
}