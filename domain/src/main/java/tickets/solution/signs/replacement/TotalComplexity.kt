package tickets.solution.signs.replacement

internal val Iterable<SignReplacement>.totalComplexity: Int
    get() = fold(initial = 0) { acc, repl -> acc + repl.complexity }