package tickets.solution.signs.position

interface SignPosition : Comparable<SignPosition> {

    val value: Int

    companion object {

        @JvmStatic
        val FIRST: SignPosition
            get() = IntSignPosition(0)

        @JvmStatic
        val LAST: SignPosition
            get() = IntSignPosition(4)
    }
}