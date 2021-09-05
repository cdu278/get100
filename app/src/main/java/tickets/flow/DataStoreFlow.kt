package tickets.flow

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow

@Suppress("FunctionName")
fun <T> DataStoreFlow(
    dataStore: DataStore<T>,
): Flow<T> {
    return dataStore.data
}