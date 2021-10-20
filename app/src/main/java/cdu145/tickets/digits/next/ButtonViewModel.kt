package cdu145.tickets.digits.next

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import cdu145.actual.Actual
import cdu145.tickets.number.TicketNumber
import cdu145.tickets.solution.signs.SolutionSigns

class NextNumberButtonViewModel(
    private val actualNumber: Actual.Mutable<TicketNumber>,
    private val nextNumber: Actual<TicketNumber>,
    private val solutionSigns: Actual.Mutable<SolutionSigns>,
) : ViewModel() {

    fun loadNextNumber() {
        viewModelScope.launch {
            actualNumber.mutate(nextNumber.value())
            solutionSigns.mutate(SolutionSigns.Empty)
        }
    }
}