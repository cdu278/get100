package tickets.solution.signs.position

@Suppress("TestFunctionName")
internal fun RangeSignPositions(from: Int, to: Int) : Iterable<SignPosition> {
    return List(size = to - from + 1) { SignPosition(from + it) }
}