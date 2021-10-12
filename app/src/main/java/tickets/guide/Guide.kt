package tickets.guide

import cdu145.tickets.R
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getScopeOrNull
import org.koin.core.scope.Scope

object Guide : KoinScopeComponent {

    fun pages(): List<Page> {
        return listOf(
            Page(
                imageResId = R.drawable.guide_goal,
                textResId = R.string.guide_goal,
            ),
            Page(
                imageResId = R.drawable.guide_operations_order,
                textResId = R.string.guide_operations_order,
            ),
            Page(
                imageResId = R.drawable.guide_leading_zeros,
                textResId = R.string.guide_leading_zeros,
            ),
            Page(
                imageResId = R.drawable.guide_hint,
                textResId = R.string.guide_hint,
            ),
        )
    }

    override val scope: Scope
        get() = checkNotNull(getScopeOrNull()) { "Scope is not created" }
}