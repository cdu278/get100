package cdu145.tickets.guide

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import cdu145.actual.Actual
import cdu145.actual.DataStoreActual
import cdu145.ui.state.DialogState.Hidden

val GuideDialogStateFlow = StringQualifier("GuideDialogState")
val GuideCompletedFlag = StringQualifier("GuideCompletedFlag")

val Pages = StringQualifier("Pages")
val PageCount = StringQualifier("PageCount")

val GuideModule = module {
    scope<Guide> {
        scoped(Pages) { Guide.pages() }
        scoped(PageCount) { get<List<*>>(Pages).size }
    }

    factory<Actual.Mutable<Boolean>>(GuideCompletedFlag) {
        DataStoreActual(get<Context>().guideCompletedFlagDataStore)
    } bind Actual::class

    single(GuideDialogStateFlow) { MutableStateFlow(Hidden) }

    viewModel {
        GuideDialogViewModel(
            get(GuideDialogStateFlow),
            get(GuideCompletedFlag),
        )
    }
}