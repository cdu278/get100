package tickets.hint.restoring

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

interface CleanUpRestoringHints {

    operator fun invoke()
}

fun CleanUpRestoringHints(
    dao: RestoringHintsDao,
    scope: CoroutineScope,
): CleanUpRestoringHints {
    return object : CleanUpRestoringHints {

        override fun invoke() {
            scope.launch {
                dao.transaction {
                    all().forEach {
                        val timeToRestore = it.timeToRestore
                        if (timeToRestore <= 0) {
                            delete(it)
                        } else {
                            launch {
                                delay(timeToRestore)
                                delete(it)
                            }
                        }
                    }
                }
            }
        }

        private val RestoringHint.timeToRestore: Long
            get() = HintRestorationTime - (System.currentTimeMillis() - this.usedAt)
    }
}