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