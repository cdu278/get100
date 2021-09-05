package tickets.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.dsl.onClose
import tickets.di.qualifier.ApplicationCoroutineScope
import tickets.digits.DigitCardsViewModelImpl
import tickets.digits.DigitsFlow
import tickets.digits.StubDigitsFlow

val AppModule = module {
    single(ApplicationCoroutineScope) {
        CoroutineScope(SupervisorJob())
    } onClose { it?.cancel() }

    factory(DigitsFlow) { StubDigitsFlow } bind Flow::class

    viewModel { DigitCardsViewModelImpl(get(DigitsFlow)) }
}