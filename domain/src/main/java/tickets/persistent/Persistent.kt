package tickets.persistent

interface Persistent<out T> {

    suspend fun actual(): T

    interface Mutable<T> : Persistent<T> {

        suspend fun mutate(newValue: T)
    }
}