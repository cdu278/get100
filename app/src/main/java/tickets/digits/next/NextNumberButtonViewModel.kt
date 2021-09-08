package tickets.digits.next

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.number.TicketNumber
import tickets.solution.signs.SolutionSigns

interface NextNumberButtonViewModel {

    fun loadNextNumber()

    companion object {

        val Preview = object : NextNumberButtonViewModel {

            override fun loadNextNumber() {
                // No-op
            }
        }
    }
}

class NextNumberButtonViewModelImpl(
    private val actualNumber: Actual.Mutable<TicketNumber>,
    private val nextNumber: Actual<TicketNumber>,
    private val solutionSigns: Actual.Mutable<SolutionSigns>,
) : ViewModel(), NextNumberButtonViewModel {

    override fun loadNextNumber() {
        viewModelScope.launch {
            actualNumber.mutate(nextNumber.value())
            solutionSigns.mutate(SolutionSigns.Empty)
        }
    }
}