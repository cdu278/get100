package tickets.number.good

import tickets.number.TicketNumber

interface GoodTicketNumbers {

    suspend fun next(): TicketNumber
}