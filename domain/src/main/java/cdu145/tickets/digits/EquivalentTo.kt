package cdu145.tickets.digits

infix fun TicketDigits.notEquivalentTo(other: TicketDigits): Boolean {
    repeat(6) { i ->
        if (this[i] != other[i]) return true
    }
    return false
}