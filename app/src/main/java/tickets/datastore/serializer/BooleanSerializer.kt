package tickets.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.*

class BooleanSerializer(
    override val defaultValue: Boolean,
    private val intSerializer: Serializer<Int> = IntSerializer(defaultValue.asInt()),
) : Serializer<Boolean> {

    override suspend fun readFrom(input: InputStream): Boolean {
        return try {
            intSerializer.readFrom(input).asBoolean()
        } catch (e: CorruptionException) {
            throw CorruptionException("Cannot read boolean value.", e)
        }
    }

    override suspend fun writeTo(t: Boolean, output: OutputStream) {
        try {
            intSerializer.writeTo(t.asInt(), output)
        } catch (e: CorruptionException) {
            throw CorruptionException("Cannot write boolean value.", e)
        }
    }
}

private fun Boolean.asInt(): Int = if (this) 1 else 0

private fun Int.asBoolean(): Boolean = this == 1