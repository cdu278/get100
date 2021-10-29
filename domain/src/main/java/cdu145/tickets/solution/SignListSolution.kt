package cdu145.tickets.solution

import cdu145.tickets.solution.signs.SolutionSign

class SignListSolution(
    private val signs: List<SolutionSign>,
) : Solution {

    init {
        signs.count().let {
            require(it == 5) { "Invalid sign count: $it" }
        }
    }

    override fun signAt(position: Int): SolutionSign = signs[position]
}