package tickets.solution.signs

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import tickets.solution.signs.SolutionSign.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

val Context.solutionSignsDataStore: DataStore<SolutionSigns>
        by dataStore(
            fileName = "solution_signs.bin",
            serializer = SolutionSignsSerializer,
        )

@Suppress("BlockingMethodInNonBlockingContext")
private object SolutionSignsSerializer : Serializer<SolutionSigns> {

    override val defaultValue: SolutionSigns
        get() = SolutionSigns.Empty

    private class ByteArraySolutionSigns(
        private val array: ByteArray,
    ) : SolutionSigns {

        override fun get(position: Int): SolutionSign {
            return SolutionSign.values()[array[position].toInt()]
        }
    }

    override suspend fun readFrom(input: InputStream): SolutionSigns {
        return try {
            ByteArraySolutionSigns(input.readBytes())
        } catch (e: IOException) {
            throw CorruptionException("Cannot read solution signs.", e)
        }
    }

    private val SolutionSign.byteOrdinal: Int
        get() = when (this) {
            PLUS -> 0
            MINUS -> 1
            TIMES -> 2
            DIV -> 3
            NONE -> 4
        }

    override suspend fun writeTo(t: SolutionSigns, output: OutputStream) {
        try {
            repeat(5) { i ->
                output.write(t[i].byteOrdinal)
            }
        } catch (e: IOException) {
            throw CorruptionException("Cannot write solution signs.", e)
        }
    }
}