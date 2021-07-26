package tickets.solution.signs

enum class ArithmeticSign : SolutionSign {

    PLUS {

        override val value: ArithmeticSign
            get() = this
    },
    MINUS {

        override val value: ArithmeticSign
            get() = this
    },
    TIMES {

        override val value: ArithmeticSign
            get() = this
    },
    DIV {

        override val value: ArithmeticSign
            get() = this
    },
    NONE {

        override val value: ArithmeticSign
            get() = this
    }
}