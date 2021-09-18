package tickets.actual

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first

class DataStoreMutable<T>(
    private val dataStore: DataStore<T>,
) : Actual.Mutable<T> {

    override suspend fun value(): T = dataStore.data.first()

    override suspend fun mutate(newValue: T) {
        dataStore.updateData { newValue }
    }

    override suspend fun mutate(mutation: Actual.Mutable.Mutation<T>) {
        dataStore.updateData { mutation.apply(it) }
    }
}