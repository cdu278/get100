package tickets.digits

interface TicketDigits {

    operator fun get(position: Int): TicketDigit

    object Zeros : TicketDigits {

        override fun get(position: Int): TicketDigit = TicketDigit(0)
    }
}