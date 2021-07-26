package tickets.digits

import tickets.digits.position.DigitPosition

internal class FragmentOfTicketDigits(
    private val digits: List<TicketDigit>,
) : TicketDigits {

    constructor(vararg digits: Int) : this(digits.map(::IntTicketDigit))

    override fun get(position: DigitPosition): TicketDigit = digits[position.value]
}