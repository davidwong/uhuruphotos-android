package com.savvasdalkitsis.uhuruphotos.feature.search.view.implementation.seam.actions

import com.savvasdalkitsis.uhuruphotos.feature.search.view.implementation.seam.SearchActionsContext
import com.savvasdalkitsis.uhuruphotos.feature.search.view.implementation.seam.SearchEffect
import com.savvasdalkitsis.uhuruphotos.feature.search.view.implementation.seam.SearchMutation
import com.savvasdalkitsis.uhuruphotos.feature.search.view.implementation.ui.state.SearchState
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.EffectHandler
import kotlinx.coroutines.flow.flow

data class RemoveFromRecentSearches(val query: String) : SearchAction() {
    context(SearchActionsContext) override fun handle(
        state: SearchState,
        effect: EffectHandler<SearchEffect>
    ) = flow<SearchMutation> {
        searchUseCase.removeFromRecentSearches(query)
    }
}