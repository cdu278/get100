package cdu145.tickets.digits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import cdu145.loadable.Loadable
import cdu145.loadable.Loadable.NotReady
import cdu145.loadable.Loadable.Ready

class DigitCardsViewModel(
    private val digitsFlow: Flow<TicketDigits>,
) : ViewModel() {

    private val _digits = MutableStateFlow<Loadable<TicketDigits>>(NotReady)

    val digits: StateFlow<Loadable<TicketDigits>>
        get() = _digits

    init {
        viewModelScope.launch {
            digitsFlow.collect {
                if (it notEquivalentTo TicketDigits.Zeros) {
                    _digits.value = Ready(it)
                }
            }
        }
    }
}