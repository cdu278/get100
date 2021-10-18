package cdu145.tickets.hint.restoring

import cdu145.actual.Actual

class ActualRemainingRestorationTime(
    private val restoringHintsDao: RestoringHintsDao,
) : Actual<RemainingRestorationTime> {

    override suspend fun value(): RemainingRestorationTime {
        return RemainingRestorationTime(
            millis = HintRestorationTime - (System.currentTimeMillis() - restoringHintsDao.earliestUsedAt()),
        )
    }
}