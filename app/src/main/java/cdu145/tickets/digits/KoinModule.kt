package cdu145.tickets.digits

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import cdu145.tickets.digits.next.NextNumberButtonViewModel
import cdu145.flow.MappingFlow
import cdu145.tickets.number.ActualTicketNumber
import cdu145.tickets.number.NextTicketNumber
import cdu145.tickets.number.TicketNumberFlow
import cdu145.tickets.solution.signs.ActualSolutionSigns

val TicketDigitsFlow = StringQualifier("TicketDigitsFlow")

val TicketDigitsModule = module {
    factory(TicketDigitsFlow) { MappingFlow(get(TicketNumberFlow), ::digitsOf) }
    viewModel { DigitCardsViewModel(get(TicketDigitsFlow)) }
    viewModel {
        NextNumberButtonViewModel(
            actualNumber = get(ActualTicketNumber),
            nextNumber = get(NextTicketNumber),
            solutionSigns = get(ActualSolutionSigns),
        )
    }
}