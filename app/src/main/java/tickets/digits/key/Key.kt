package tickets.digits.key

import tickets.digits.TicketDigits

val TicketDigits.key: Any
    get() = KeyStore.keyFor(this)