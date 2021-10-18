package cdu145.datastore.serializer

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

class MappingSerializer<T, Serializable>(
    private val delegate: Serializer<Serializable>,
    private val transform: Transform<T, Serializable>,
    override val defaultValue: T,
) : Serializer<T> {

    interface Transform<T, Serializable> {

        suspend fun toSerializable(t: T): Serializable

        suspend fun fromSerializable(serializable: Serializable): T
    }

    override suspend fun readFrom(input: InputStream): T {
        return transform.fromSerializable(delegate.readFrom(input))
    }

    override suspend fun writeTo(t: T, output: OutputStream) {
        return delegate.writeTo(transform.toSerializable(t), output)
    }
}