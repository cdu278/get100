package tickets.solution.signs

import tickets.solution.signs.position.SignPosition

interface SolutionSigns {

    operator fun get(position: SignPosition): SolutionSign
}