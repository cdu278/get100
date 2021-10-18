package cdu145.loadable

sealed interface Loadable<out T> {

    object NotReady : Loadable<Nothing>

    data class Ready<T>(val value: T) : Loadable<T>
}