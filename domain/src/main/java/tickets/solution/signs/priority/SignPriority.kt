package tickets.solution.signs.priority

interface SignPriority : Comparable<SignPriority> {

    val value: Int
}