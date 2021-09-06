package tickets.solution.signs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.solution.signs.position.SignPosition

interface SignButtonsViewModel {

    val shown: Flow<Boolean>

    fun chooseSign(sign: SolutionSign)

    companion object {

        val Preview = object : SignButtonsViewModel {

            override val shown: Flow<Boolean> = emptyFlow()

            override fun chooseSign(sign: SolutionSign) {
                // No-op
            }
        }
    }
}

class SignButtonsViewModelImpl(
    private val solutionSigns: Actual.Mutable<SolutionSigns>,
    private val highlightedSignPosition: Actual<SignPosition>,
) : ViewModel(), SignButtonsViewModel {

    override val shown: Flow<Boolean> = flowOf(true)

    private class AlteredSolutionSigns(
        private val original: SolutionSigns,
        private val targetPosition: SignPosition,
        private val newSign: SolutionSign,
    ) : SolutionSigns {

        override fun get(position: SignPosition): SolutionSign {
            return if (position.value == targetPosition.value) {
                newSign
            } else {
                original[position]
            }
        }
    }

    override fun chooseSign(sign: SolutionSign) {
        viewModelScope.launch {
            solutionSigns.mutate(
                AlteredSolutionSigns(
                    original = solutionSigns.value(),
                    targetPosition = highlightedSignPosition.value(),
                    newSign = sign,
                ),
            )
        }
    }
}