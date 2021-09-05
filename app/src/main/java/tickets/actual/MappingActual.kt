package tickets.actual

class MappingActual<T, Mapped>(
    private val actualMapped: Actual<Mapped>,
    private val transform: (Mapped) -> T,
) : Actual<T> {

    override suspend fun value(): T = transform(actualMapped.value())
}