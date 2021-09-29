package tickets.hint.restoring

import kotlinx.coroutines.delay

interface RemainingRestorationTime {

    val seconds: Int

    val over: Boolean

    suspend fun tick(): RemainingRestorationTime
}

fun RemainingRestorationTime(
    millis: Long,
): RemainingRestorationTime {
    return object : RemainingRestorationTime {

        override val seconds: Int
            get() {
                val seconds = (millis / 1000).toInt()
                return if (millis % 1000 == 0L) seconds else seconds + 1
            }

        override val over: Boolean
            get() = millis == 0L

        override suspend fun tick(): RemainingRestorationTime {
            require(!over) { "Restoration time is over" }
            val delayTime = (millis % 1000).takeIf { it != 0L } ?: 1000L
            delay(delayTime)
            return RemainingRestorationTime(millis - delayTime)
        }
    }
}