package tickets.digits

import tickets.digits.position.DigitPosition

interface TicketDigits {

    operator fun get(position: DigitPosition): TicketDigit
}