package tickets.digits

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import tickets.actual.Actual
import tickets.actual.MappingActual
import tickets.digits.next.NextNumberButtonViewModelImpl
import tickets.flow.MappingFlow
import tickets.number.ActualTicketNumber
import tickets.number.NextTicketNumber
import tickets.number.TicketNumberFlow

val TicketDigitsFlow = StringQualifier("TicketDigitsFlow")
val ActualTicketDigits = StringQualifier("ActualTicketDigits")

val TicketDigitsModule = module {
    factory(TicketDigitsFlow) { MappingFlow(get(TicketNumberFlow), ::DigitsOf) }
    factory<Actual<TicketDigits>>(ActualTicketDigits) {
        MappingActual(get(ActualTicketNumber), ::DigitsOf)
    }
    viewModel {
        NextNumberButtonViewModelImpl(
            actualNumber = get(ActualTicketNumber),
            nextNumber = get(NextTicketNumber),
        )
    }
}