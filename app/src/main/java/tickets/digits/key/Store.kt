package tickets.digits.key

import tickets.digits.TicketDigits

object KeyStore {

    private var currentDigits: TicketDigits = TicketDigits.Zeros

    private var currentDigitsKey: Any = 0

    fun keyFor(digits: TicketDigits): Any {
        if (digits differsFrom currentDigits) {
            currentDigits = digits
            currentDigitsKey = calculateKeyFor(currentDigits)
        }
        return currentDigitsKey
    }

    private fun calculateKeyFor(digits: TicketDigits): Any {
        var key = digits[0]
        repeat(5) { i ->
            key *= 10
            key += digits[i]
        }
        return key
    }

    private infix fun TicketDigits.differsFrom(other: TicketDigits): Boolean {
        repeat(6) { i ->
            if (this[i] != other[i]) return true
        }
        return false
    }
}