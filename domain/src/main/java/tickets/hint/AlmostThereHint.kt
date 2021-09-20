package tickets.hint

internal class AlmostThereHint(
    private val almostThereDialog: AlmostThereDialog,
) : Hint {

    override suspend fun use() {
        almostThereDialog.show()
    }
}