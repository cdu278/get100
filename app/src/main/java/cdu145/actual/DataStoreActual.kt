package cdu145.actual

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first

class DataStoreActual<T>(
    private val dataStore: DataStore<T>,
) : Actual.Mutable<T> {

    override suspend fun value(): T = dataStore.data.first()

    override suspend fun mutate(newValue: T) {
        dataStore.updateData { newValue }
    }

    override suspend fun mutate(mutation: Mutation<T>) {
        dataStore.updateData { mutation.apply(it) }
    }
}