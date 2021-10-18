package cdu145.actual

interface Actual<out T> {

    suspend fun value(): T

    interface Mutable<T> : Actual<T> {

        suspend fun mutate(newValue: T)

        fun interface Mutation<T> {

            suspend fun apply(actualValue: T): T
        }

        suspend fun mutate(mutation: Mutation<T>)
    }
}