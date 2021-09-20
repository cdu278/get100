package tickets.hint.available

object NoOpHintRestoration : HintRestoration {

    override fun schedule() {
        // No-op
    }
}