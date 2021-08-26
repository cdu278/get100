package tickets.digits

import tickets.digits.position.DigitPosition

class ListTicketDigits(
    private val digits: List<TicketDigit>,
) : TicketDigits {

    init {
        digits.count().let {
            require(it == 6) { "Invalid digit count: $it" }
        }
    }

    override fun get(position: DigitPosition): TicketDigit = digits[position.value]
}