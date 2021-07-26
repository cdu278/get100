package tickets.solution.result.value.affectable

interface AffectableBySolutionResultValue<out R> {

    fun affectedByUndefined(): R

    fun affectedByDefined(value: Double): R
}