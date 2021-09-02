package tickets.solution.signs

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface SignButtonsViewModel {

    val shown: Flow<Boolean>

    fun chooseSign(sign: SolutionSign)

    companion object {

        val Preview = object : SignButtonsViewModel {

            override val shown: Flow<Boolean> = emptyFlow()

            override fun chooseSign(sign: SolutionSign) {
                // No-op
            }
        }
    }
}

class SignButtonsViewModelImpl : ViewModel(),
    SignButtonsViewModel by SignButtonsViewModel.Preview