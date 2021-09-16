package tickets.digits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tickets.digits.DigitCardsState.NotReady
import tickets.digits.DigitCardsState.Ready

interface DigitCardsViewModel {

    val state: StateFlow<DigitCardsState>

    object Preview : DigitCardsViewModel {

        override val state: StateFlow<DigitCardsState> = MutableStateFlow(NotReady)
    }
}

class DigitCardsViewModelImpl(
    private val digitsFlow: Flow<TicketDigits>,
) : ViewModel(), DigitCardsViewModel {

    private val _state = MutableStateFlow<DigitCardsState>(NotReady)

    override val state: StateFlow<DigitCardsState>
        get() = _state

    init {
        viewModelScope.launch {
            digitsFlow.collect {
                if (it notEquivalentTo TicketDigits.Zeros) {
                    _state.value = Ready(it)
                }
            }
        }
    }
}