package cdu145.tickets.digits

interface TicketDigits {

    operator fun get(position: Int): Int

    object Zeros : TicketDigits {

        override fun get(position: Int): Int = 0
    }
}