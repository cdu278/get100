package tickets.digits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tickets.loadable.Loadable
import tickets.loadable.Loadable.NotReady
import tickets.loadable.Loadable.Ready

interface DigitCardsViewModel {

    val digits: StateFlow<Loadable<TicketDigits>>

    object Preview : DigitCardsViewModel {

        override val digits: StateFlow<Loadable<TicketDigits>> = MutableStateFlow(NotReady)
    }
}

class DigitCardsViewModelImpl(
    private val digitsFlow: Flow<TicketDigits>,
) : ViewModel(), DigitCardsViewModel {

    private val _digits = MutableStateFlow<Loadable<TicketDigits>>(NotReady)

    override val digits: StateFlow<Loadable<TicketDigits>>
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