package tickets.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.*

@Suppress("BlockingMethodInNonBlockingContext")
object IntSerializer : Serializer<Int> {

    override val defaultValue: Int
        get() = 0

    override suspend fun readFrom(input: InputStream): Int {
        return try {
            DataInputStream(input).readInt()
        } catch (e: IOException) {
            throw CorruptionException("Cannot read int value.", e)
        }
    }

    override suspend fun writeTo(t: Int, output: OutputStream) {
        try {
            DataOutputStream(output).writeInt(t)
        } catch (e: IOException) {
            throw CorruptionException("Cannot write int value.", e)
        }
    }
}