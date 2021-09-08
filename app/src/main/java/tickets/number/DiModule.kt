package tickets.number

import android.content.Context
import kotlinx.coroutines.flow.Flow
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.actual.Actual
import tickets.actual.DataStoreMutable
import tickets.coroutine.scope.ApplicationCoroutineScope
import tickets.flow.DataStoreFlow
import tickets.number.good.RandomGoodNumberFromRes

val TicketNumberFlow = StringQualifier("TicketNumberFlow")
val ActualTicketNumber = StringQualifier("TicketNumberActual")
val TicketNumberDataStore = StringQualifier("TicketNumberDataStore")
val NextTicketNumber = StringQualifier("NextTicketNumber")

val TicketNumberModule = module {
    factory(TicketNumberDataStore) { get<Context>().ticketNumberDataStore }
    factory<Actual<TicketNumber>>(ActualTicketNumber) {
        DataStoreMutable(get(TicketNumberDataStore))
    } bind Actual.Mutable::class
    factory<Flow<TicketNumber>>(TicketNumberFlow) { DataStoreFlow(get(TicketNumberDataStore)) }

    factory<Actual<TicketNumber>>(NextTicketNumber) { RandomGoodNumberFromRes(context = get()) }
    factory {
        EnsureTicketNumberCreated(
            ticketNumber = get(ActualTicketNumber),
            firstNumber = get(NextTicketNumber),
            scope = get(ApplicationCoroutineScope),
        )
    }
}