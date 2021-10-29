package cdu145.tickets.solution

import cdu145.tickets.solution.signs.SolutionSign

interface Solution {

    fun signAt(position: Int): SolutionSign

    companion object {

        val Empty: Solution = object : Solution {

            override fun signAt(position: Int): SolutionSign = SolutionSign.None
        }
    }
}

fun Solution.asIterable(): Iterable<SolutionSign> = List(size = 5) { i -> this.signAt(i) }