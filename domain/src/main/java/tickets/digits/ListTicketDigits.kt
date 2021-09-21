package tickets.digits

class ListTicketDigits(
    private val digits: List<TicketDigit>,
) : TicketDigits {

    init {
        digits.count().let {
            require(it == 6) { "Invalid digit count: $it" }
        }
    }

    override fun get(position: Int): TicketDigit = digits[position]
}