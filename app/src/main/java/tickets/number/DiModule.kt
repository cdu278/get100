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
import tickets.number.good.GoodTicketNumbers
import tickets.number.good.MagicNumbers

val TicketNumberFlow = StringQualifier("TicketNumberFlow")
val ActualTicketNumber = StringQualifier("TicketNumberActual")
val TicketNumberDataStore = StringQualifier("TicketNumberDataStore")

val TicketNumberModule = module {
    factory(TicketNumberDataStore) { get<Context>().ticketNumberDataStore }
    factory<Actual<TicketNumber>>(ActualTicketNumber) {
        DataStoreMutable(get(TicketNumberDataStore))
    } bind Actual.Mutable::class
    factory<Flow<TicketNumber>>(TicketNumberFlow) { DataStoreFlow(get(TicketNumberDataStore)) }
    factory {
        EnsureTicketNumberCreated(
            ticketNumber = get(ActualTicketNumber),
            goodNumbers = get(),
            scope = get(ApplicationCoroutineScope),
        )
    }
    factory<GoodTicketNumbers> { MagicNumbers() }
}