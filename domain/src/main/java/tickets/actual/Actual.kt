package tickets.actual

interface Actual<out T> {

    suspend fun value(): T

    interface Mutable<T> : Actual<T> {

        suspend fun mutate(newValue: T)
    }
}