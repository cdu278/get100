package tickets.digits

import tickets.digits.position.DigitPosition

internal class VarargTicketDigits(
    private vararg val digits: Int,
) : TicketDigits {

    init {
        digits.count().let {
            require(it == 6) { "Invalid digit count: $it" }
        }
    }

    override fun get(position: DigitPosition): TicketDigit = TicketDigit(digits[position.value])
}