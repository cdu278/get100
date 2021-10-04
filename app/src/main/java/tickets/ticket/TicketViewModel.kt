package tickets.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import tickets.digits.TicketDigits
import tickets.loadable.Loadable
import tickets.loadable.Loadable.NotReady
import tickets.loadable.Loadable.Ready

class TicketViewModel(
    digitsFlow: Flow<TicketDigits>,
) : ViewModel() {

    private val _digits = MutableStateFlow<Loadable<TicketDigits>>(NotReady)

    val digits: StateFlow<Loadable<TicketDigits>>
        get() = _digits

    init {
        digitsFlow
            .onEach { _digits.value = Ready(it) }
            .launchIn(viewModelScope)
    }
}