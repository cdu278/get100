package tickets.hint.available

import tickets.hint.available.AvailableHints.Companion.MaxCount
import tickets.hint.restoring.RestoringHint
import tickets.hint.restoring.RestoringHintDeletion
import tickets.hint.restoring.RestoringHintsDao

fun AvailableHints(
    restoringHintsDao: RestoringHintsDao,
    restoringHintDeletion: RestoringHintDeletion,
): AvailableHints {
    return object : AvailableHints {

        override suspend fun acquire(block: suspend () -> Unit) {
            restoringHintsDao.transaction {
                check(count() < MaxCount) { "No hints available" }

                val restoringHint = RestoringHint()
                insert(restoringHint)
                restoringHintDeletion.schedule(restoringHint)

                block.invoke()
            }
        }
    }
}