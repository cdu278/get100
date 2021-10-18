package cdu145.tickets.guide

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import cdu145.datastore.serializer.BooleanSerializer

val Context.guideCompletedFlagDataStore: DataStore<Boolean>
        by dataStore(
            fileName = "guide_completed.bin",
            serializer = BooleanSerializer(defaultValue = false),
        )