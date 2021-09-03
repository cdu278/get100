package tickets.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.digits.DigitCardsViewModelImpl
import tickets.digits.DigitsFlowable
import tickets.digits.StubFlowableDigits
import tickets.flowable.Flowable

val AppModule = module {
    factory(DigitsFlowable) { StubFlowableDigits } bind Flowable::class

    viewModel { DigitCardsViewModelImpl(get(DigitsFlowable)) }
}