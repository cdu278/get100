package cdu145.tickets.solution

import cdu145.tickets.solution.sign.SolutionSign

class AlteredSolution(
    private val original: Solution,
    private val targetPosition: Int,
    private val newSign: SolutionSign,
) : Solution {

    override fun signAt(position: Int): SolutionSign {
        return if (position == targetPosition) {
            newSign
        } else {
            original.signAt(position)
        }
    }
}