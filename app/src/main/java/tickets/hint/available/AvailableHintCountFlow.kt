package tickets.hint.available

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tickets.hint.restoring.RestoringHintsDao

@Suppress("FunctionName")
fun AvailableHintCountFlow(
    restoringHintsDao: RestoringHintsDao,
): Flow<Int> {
    return restoringHintsDao.countFlow().map { AvailableHints.MaxCount - it }
}