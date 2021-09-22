package tickets.hint.available

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tickets.hint.available.AvailableHints.Companion.MaxCount
import tickets.hint.restoring.HintRestorationTime
import tickets.hint.restoring.RestoringHint
import tickets.hint.restoring.RestoringHintsDao

fun AvailableHints(
    restoringHintsDao: RestoringHintsDao,
    scope: CoroutineScope,
): AvailableHints {
    return object : AvailableHints {

        override suspend fun acquire(block: suspend () -> Unit) {
            restoringHintsDao.transaction {
                check(count() < MaxCount) { "No hints available" }

                val restoringHint = RestoringHint()
                insert(restoringHint)
                scope.launch {
                    println("Deletion delayed by $HintRestorationTime ms.")
                    delay(HintRestorationTime)
                    delete(restoringHint)
                }

                block.invoke()
            }
        }
    }
}