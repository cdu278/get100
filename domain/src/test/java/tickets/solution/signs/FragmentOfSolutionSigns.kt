package tickets.solution.signs

import tickets.solution.signs.position.SignPosition

internal class FragmentOfSolutionSigns(
    private vararg val signs: SolutionSign,
) : SolutionSigns {

    override fun get(position: SignPosition): SolutionSign = signs[position.value]
}