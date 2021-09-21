package tickets.solution.gap

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import tickets.datastore.serializer.IntSerializer

val Context.highlightedGapDataStore: DataStore<Int>
        by dataStore(
            fileName = "highlighted_position.bin",
            serializer = IntSerializer(defaultValue = 0),
        )