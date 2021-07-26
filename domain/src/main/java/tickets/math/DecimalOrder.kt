package tickets.math

internal interface DecimalOrder {

    fun shift(number: Double): Double
}

internal class DecimalOrderOf(
    private val number: Double,
) : DecimalOrder {

    private val order: Double
        get() {
            var powerOfTen = 10.0
            while (number >= powerOfTen) {
                powerOfTen *= 10
            }
            return powerOfTen
        }

    override fun shift(number: Double): Double = number * order
}
