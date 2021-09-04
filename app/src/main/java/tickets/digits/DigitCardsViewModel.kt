package tickets.digits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface DigitCardsViewModel {

    val state: StateFlow<DigitCardsState>

    object Preview : DigitCardsViewModel {

        override val state: StateFlow<DigitCardsState> = MutableStateFlow(
            DigitCardsState(
                loaded = true,
                TicketDigits.Zeros,
            )
        )
    }
}

class DigitCardsViewModelImpl(
    private val digitsFlow: Flow<Deferred<TicketDigits>>,
) : ViewModel(), DigitCardsViewModel {

    private val _state = MutableStateFlow(
        DigitCardsState(
            loaded = false,
            TicketDigits.Zeros,
        )
    )

    override val state: StateFlow<DigitCardsState>
        get() = _state

    init {
        viewModelScope.launch {
            digitsFlow.collect { deferredDigits ->
                _state.value = DigitCardsState(
                    loaded = false,
                    state.value.digits,
                )
                val loadedDigits = deferredDigits.await()
                _state.value = DigitCardsState(
                    loaded = true,
                    loadedDigits,
                )
            }
        }
    }
}