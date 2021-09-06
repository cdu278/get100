package tickets.number

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import tickets.actual.Actual

class MutableTicketNumber(
    private val dataStore: DataStore<TicketNumber>,
    private val readyFlow: MutableStateFlow<Boolean>,
) : Actual.Mutable<TicketNumber> {

    override suspend fun value(): TicketNumber = dataStore.data.first()

    override suspend fun mutate(newValue: TicketNumber) {
        readyFlow.value = false
        dataStore.updateData { newValue }
        readyFlow.value = true
    }
}