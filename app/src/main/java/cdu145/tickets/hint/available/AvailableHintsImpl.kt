package cdu145.tickets.hint.available

import cdu145.tickets.hint.available.AvailableHints.Companion.MaxCount
import cdu145.tickets.hint.restoring.RestoringHint
import cdu145.tickets.hint.restoring.RestoringHintDeletion
import cdu145.tickets.hint.restoring.RestoringHintsDao

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