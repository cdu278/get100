package tickets.solution.signs

import tickets.solution.signs.position.SignPosition

class AlteredSolutionSigns(
    private val original: SolutionSigns,
    private val targetPosition: SignPosition,
    private val newSign: SolutionSign,
) : SolutionSigns {

    override fun get(position: SignPosition): SolutionSign {
        return if (position.value == targetPosition.value) {
            newSign
        } else {
            original[position]
        }
    }
}