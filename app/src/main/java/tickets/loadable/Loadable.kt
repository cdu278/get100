package tickets.loadable

sealed interface Loadable<out T> {

    object NotReady : Loadable<Nothing>

    data class Ready<T>(val value: T) : Loadable<T>
}