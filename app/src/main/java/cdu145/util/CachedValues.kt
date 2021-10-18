package cdu145.util

import androidx.compose.runtime.Composable

interface CachedValues<out T> {

    @Composable
    operator fun get(position: Int): T
}

@Suppress("FunctionName")
fun <T> CachedValues(
    lazyValueAt: @Composable (position: Int) -> T,
    backingList: MutableList<T> = mutableListOf(),
): CachedValues<T> {
    return object : CachedValues<T> {

        @Composable
        override fun get(position: Int): T {
            return if (position in backingList.indices) {
                backingList[position]
            } else {
                lazyValueAt(position).also { backingList.add(it) }
            }
        }
    }
}