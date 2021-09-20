package tickets.solution.signs

import tickets.solution.signs.position.SignPosition

interface SolutionSigns {

    operator fun get(position: SignPosition): SolutionSign

    companion object {

        val Empty: SolutionSigns = object : SolutionSigns {

            override fun get(position: SignPosition): SolutionSign = ArithmeticSign.NONE
        }
    }
}

operator fun SolutionSigns.get(position: Int): SolutionSign = get(SignPosition(position))

fun SolutionSigns.asIterable(): Iterable<SolutionSign> = List(size = 5) { i -> this[i] }