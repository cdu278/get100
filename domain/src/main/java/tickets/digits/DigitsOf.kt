package tickets.digits

import tickets.number.TicketNumber
import kotlin.math.pow

private fun TicketNumber.digitAt(position: Int): Int {
    return (this.value / 10.0.pow(5 - position).toInt()) % 10
}

@Suppress("FunctionName")
fun digitsOf(ticketNumber: TicketNumber): TicketDigits {
    return ListTicketDigits(
        List(size = 6) { i -> ticketNumber.digitAt(i) },
    )
}