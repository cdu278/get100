package cdu145.tickets.solution.signs

interface SolutionSigns {

    operator fun get(position: Int): SolutionSign

    companion object {

        val Empty: SolutionSigns = object : SolutionSigns {

            override fun get(position: Int): SolutionSign = SolutionSign.NONE
        }
    }
}

fun SolutionSigns.asIterable(): Iterable<SolutionSign> = List(size = 5) { i -> this[i] }