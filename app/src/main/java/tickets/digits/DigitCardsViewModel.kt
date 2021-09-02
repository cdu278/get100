package tickets.digits

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface DigitCardsViewModel {

    val state: Flow<DigitCardsState>

    object Preview : DigitCardsViewModel {

        override val state: Flow<DigitCardsState> = emptyFlow()
    }
}

class DigitCardsViewModelImpl : ViewModel(), DigitCardsViewModel by DigitCardsViewModel.Preview