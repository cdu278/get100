package tickets.digits.next

import androidx.lifecycle.ViewModel

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

class NextNumberButtonViewModelImpl : ViewModel(),
    NextNumberButtonViewModel by NextNumberButtonViewModel.Preview