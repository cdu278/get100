package tickets.persistent

interface Persistent<out T> {

    suspend fun actual(): T
}