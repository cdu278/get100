package tickets.solution.gap

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import tickets.datastore.serializer.IntSerializer
import tickets.datastore.serializer.MappingSerializer
import tickets.solution.signs.position.SignPosition

val Context.highlightedGapDataStore: DataStore<SignPosition>
        by dataStore(
            fileName = "highlighted_position.bin",
            serializer = MappingSerializer(
                delegate = IntSerializer(),
                transform = IntTransform,
                defaultValue = SignPosition.First,
            )
        )

private object IntTransform : MappingSerializer.Transform<SignPosition, Int> {

    override suspend fun toSerializable(t: SignPosition): Int = t.value

    override suspend fun fromSerializable(serializable: Int): SignPosition {
        return SignPosition(value = serializable)
    }
}