package cdu145.tickets.solution.signs

class AlteredSolutionSigns(
    private val original: SolutionSigns,
    private val targetPosition: Int,
    private val newSign: SolutionSign,
) : SolutionSigns {

    override fun get(position: Int): SolutionSign {
        return if (position == targetPosition) {
            newSign
        } else {
            original[position]
        }
    }
}