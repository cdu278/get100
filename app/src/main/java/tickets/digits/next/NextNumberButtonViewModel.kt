package tickets.digits.next

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.number.TicketNumber
import tickets.number.good.GoodTicketNumbers

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
    private val goodNumbers: GoodTicketNumbers,
) : ViewModel(), NextNumberButtonViewModel {

    override fun loadNextNumber() {
        viewModelScope.launch { actualNumber.mutate(goodNumbers.next()) }
    }
}