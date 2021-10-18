package cdu145.actual

fun interface Mutation<T> {

    suspend fun apply(actualValue: T): T
}