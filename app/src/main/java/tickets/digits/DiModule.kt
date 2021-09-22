package tickets.digits

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import tickets.digits.next.NextNumberButtonViewModelImpl
import tickets.flow.MappingFlow
import tickets.number.ActualTicketNumber
import tickets.number.NextTicketNumber
import tickets.number.TicketNumberFlow
import tickets.solution.signs.ActualSolutionSigns

val TicketDigitsFlow = StringQualifier("TicketDigitsFlow")

val TicketDigitsModule = module {
    factory(TicketDigitsFlow) { MappingFlow(get(TicketNumberFlow), ::digitsOf) }
    viewModel { DigitCardsViewModelImpl(get(TicketDigitsFlow)) }
    viewModel {
        NextNumberButtonViewModelImpl(
            actualNumber = get(ActualTicketNumber),
            nextNumber = get(NextTicketNumber),
            solutionSigns = get(ActualSolutionSigns),
        )
    }
}