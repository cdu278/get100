package cdu145.ui.state

enum class DialogState {

    Shown, Hidden;

    companion object {

        inline fun shownIf(condition: () -> Boolean): DialogState {
            return if (condition()) Shown else Hidden
        }
    }
}