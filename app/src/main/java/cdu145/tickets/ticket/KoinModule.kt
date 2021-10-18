package cdu145.tickets.ticket

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import cdu145.tickets.digits.TicketDigitsFlow

val TicketModule = module {
    viewModel {
        TicketViewModel(
            get(TicketDigitsFlow),
        )
    }
}