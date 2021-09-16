package tickets.digits

infix fun TicketDigits.notEquivalentTo(other: TicketDigits): Boolean {
    repeat(6) { i ->
        if (this[i].value != other[i].value) return true
    }
    return false
}