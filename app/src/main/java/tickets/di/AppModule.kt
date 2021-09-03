package tickets.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tickets.digits.DigitCardsViewModelImpl

val AppModule = module {
    viewModel { DigitCardsViewModelImpl() }
}