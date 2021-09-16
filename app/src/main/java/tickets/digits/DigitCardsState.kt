package tickets.digits

sealed interface DigitCardsState {

    object NotReady : DigitCardsState

    data class Ready(
        val digits: TicketDigits,
    ) : DigitCardsState
}