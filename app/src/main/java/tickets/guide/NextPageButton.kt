package tickets.guide

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import tickets.guide.NextPageButtonState.Finish
import tickets.guide.NextPageButtonState.ShowNext

@Composable
fun NextPageButton(
    viewModel: ViewModel = getViewModel(),
) {
    val state by viewModel.nextButtonState.collectAsState()
    when (state) {
        is Finish -> FinishButton()
        is ShowNext -> ShowNextButton()
    }
}

sealed interface NextPageButtonState {

    object Finish : NextPageButtonState

    data class ShowNext(
        val pagePosition: Int,
    ) : NextPageButtonState
}

@Composable
private fun FinishButton(
    viewModel: ViewModel = getViewModel(),
) {
    Button(onClick = { viewModel.finish() }) {
        Text(
            text = stringResource(R.string.guideDialog_finish),
        )
    }
}

@Composable
private fun ShowNextButton(
    viewModel: ViewModel = getViewModel(),
    pageCount: Int = remember { Guide.scope.get(PageCount) },
) {
    Button(onClick = { viewModel.showNextPage() }) {
        val pagePosition by viewModel.pagePosition.collectAsState()
        Text(
            text(nextPagePosition = pagePosition + 1, pageCount),
        )
    }
}

@Composable
private fun text(nextPagePosition: Int, pageCount: Int): String {
    return stringResource(R.string.guideDialog_nextTemplate, nextPagePosition + 1, pageCount)
}