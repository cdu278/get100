package cdu145.tickets.digits.next

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import cdu145.actual.Actual
import cdu145.tickets.number.TicketNumber
import cdu145.tickets.solution.Solution

class NextNumberButtonViewModel(
    private val actualNumber: Actual.Mutable<TicketNumber>,
    private val nextNumber: Actual<TicketNumber>,
    private val solution: Actual.Mutable<Solution>,
) : ViewModel() {

    fun loadNextNumber() {
        viewModelScope.launch {
            actualNumber.mutate(nextNumber.value())
            solution.mutate(cdu145.tickets.solution.Solution.Empty)
        }
    }
}