package tickets.digits

data class DigitCardsState(
    val loaded: Boolean,
    val digits: TicketDigits,
) {

    companion object {

        val Preview = DigitCardsState(
            loaded = true,
            ListTicketDigits(listOf(1, 2, 3, 4, 5, 6).map(::TicketDigit)),
        )
    }
}