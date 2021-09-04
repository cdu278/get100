package tickets.di

import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.digits.DigitCardsViewModelImpl
import tickets.digits.DigitsFlow
import tickets.digits.StubDigitsFlow

val AppModule = module {
    factory(DigitsFlow) { StubDigitsFlow } bind Flow::class

    viewModel { DigitCardsViewModelImpl(get(DigitsFlow)) }
}