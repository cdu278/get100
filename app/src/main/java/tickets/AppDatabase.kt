package tickets

import androidx.room.Database
import androidx.room.RoomDatabase
import tickets.hint.restoring.RestoringHintsDao
import tickets.hint.restoring.RestoringHint

@Database(entities = [RestoringHint::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val restoringHintsDao: RestoringHintsDao
}