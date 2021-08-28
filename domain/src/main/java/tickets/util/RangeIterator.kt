package tickets.util

internal class RangeIterator<T : Comparable<T>>(
    from: T,
    private val to: T,
    private val valueAfter: (T) -> T,
) : Iterator<T> {

    private var current = from

    override fun hasNext(): Boolean = current <= to

    override fun next(): T {
        return current.also { current = valueAfter(current) }
    }
}