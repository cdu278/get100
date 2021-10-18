package cdu145.tickets.hint

internal class AlmostThereHint(
    private val almostCompletedDialog: AlmostCompletedDialog,
) : Hint {

    override suspend fun use() {
        almostCompletedDialog.show()
    }
}