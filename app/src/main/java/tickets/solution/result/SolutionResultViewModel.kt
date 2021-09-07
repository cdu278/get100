package tickets.solution.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tickets.solution.result.SolutionResultViewState.Sign.EqualTo
import tickets.solution.result.SolutionResultViewState.Value.Undefined

interface SolutionResultViewModel {

    val state: StateFlow<SolutionResultViewState>

    object Preview : SolutionResultViewModel {

        override val state: StateFlow<SolutionResultViewState> =
            MutableStateFlow(SolutionResultViewState.Initial)
    }
}

class SolutionResultViewModelImpl(
    private val resultFlow: Flow<SolutionResult>,
) : ViewModel(), SolutionResultViewModel {

    private val _state = MutableStateFlow(
        SolutionResultViewState(
            solved = false,
            sign = EqualTo,
            value = Undefined,
        )
    )

    override val state: StateFlow<SolutionResultViewState>
        get() = _state

    init {
        viewModelScope.launch {
            resultFlow.collect { _state.value = it.useFor(SolutionResultViewState.Creation) }
        }
    }
}