package cdu145.tickets

import androidx.room.Database
import androidx.room.RoomDatabase
import cdu145.tickets.hint.restoring.RestoringHintsDao
import cdu145.tickets.hint.restoring.RestoringHint

@Database(entities = [RestoringHint::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val restoringHintsDao: RestoringHintsDao
}