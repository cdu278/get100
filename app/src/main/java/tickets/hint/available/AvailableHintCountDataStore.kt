package tickets.hint.available

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import tickets.datastore.serializer.IntSerializer

val Context.availableHintCountDataStore: DataStore<Int>
        by dataStore(
            fileName = "available_hint_count.bin",
            serializer = object : Serializer<Int> by IntSerializer {

                override val defaultValue: Int
                    get() = 20
            },
        )