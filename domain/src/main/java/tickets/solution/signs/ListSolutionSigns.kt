package tickets.solution.signs

import tickets.solution.signs.position.SignPosition

class ListSolutionSigns(
    private val signs: List<SolutionSign>,
) : SolutionSigns {

    init {
        signs.count().let {
            require(it == 5) { "Invalid sign count: $it" }
        }
    }

    override fun get(position: SignPosition): SolutionSign = signs[position.value]
}