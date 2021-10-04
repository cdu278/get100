package tickets

import android.app.Application
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tickets.coroutine.scope.CoroutineScopeModule
import tickets.digits.TicketDigitsModule
import tickets.hint.HintModule
import tickets.hint.available.ReviseAvailableHints
import tickets.number.EnsureTicketNumberCreated
import tickets.number.TicketNumberModule
import tickets.solution.clear.ClearSolutionModule
import tickets.solution.correct.CorrectSolutionsModule
import tickets.solution.gap.SolutionGapsModule
import tickets.solution.result.SolutionResultModule
import tickets.solution.signs.SolutionSignsModule
import tickets.solution.signs.permutations.SignsPermutationsModule
import tickets.ticket.TicketModule

class TicketsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TicketsApplication)
            modules(CoroutineScopeModule + TicketNumberModule + TicketDigitsModule
                    + SolutionGapsModule + SolutionSignsModule + SolutionResultModule
                    + ClearSolutionModule + SignsPermutationsModule + CorrectSolutionsModule
                    + HintModule + ApplicationModule + TicketModule)
        }

        getKoin().run {
            get<EnsureTicketNumberCreated>().invoke()
            get<ReviseAvailableHints>().invoke()
        }
    }
}