package tickets.number

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.number.good.GoodTicketNumbers

interface EnsureTicketNumberCreated {

    operator fun invoke()
}

fun EnsureTicketNumberCreated(
    ticketNumber: Actual.Mutable<TicketNumber>,
    goodNumbers: GoodTicketNumbers,
    scope: CoroutineScope,
): EnsureTicketNumberCreated {
    return object : EnsureTicketNumberCreated {

        override fun invoke() {
            scope.launch(Dispatchers.Main.immediate) {
                if (ticketNumber.value() == TicketNumber.Zero) {
                    ticketNumber.mutate(goodNumbers.next())
                }
            }
        }
    }
}