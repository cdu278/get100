package tickets.digits

import tickets.number.TicketNumber
import kotlin.math.pow

private fun TicketNumber.digitAt(position: Int): TicketDigit {
    return TicketDigit(
        (this.value / 10.0.pow(5 - position).toInt()) % 10,
    )
}

@Suppress("FunctionName")
fun DigitsOf(ticketNumber: TicketNumber): TicketDigits {
    return ListTicketDigits(
        List(size = 6) { i -> ticketNumber.digitAt(i) },
    )
}