package tickets.solution.result.value

private typealias Format = String

internal class StringFormatting(
    private val format: Format,
) : SolutionResultValue.UsePurpose<String> {

    private fun Format.substitute(value: String): String = String.format(this, value)

    override fun useUndefinedValue(): String = format.substitute("<undefined>")

    override fun useValue(value: Double): String = format.substitute(value.toString())
}