package tickets.solution.signs

class ListSolutionSigns(
    private val signs: List<SolutionSign>,
) : SolutionSigns {

    init {
        signs.count().let {
            require(it == 5) { "Invalid sign count: $it" }
        }
    }

    override fun get(position: Int): SolutionSign = signs[position]
}