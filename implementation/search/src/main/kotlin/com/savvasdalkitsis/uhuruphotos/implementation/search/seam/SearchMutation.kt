/*
Copyright 2022 Savvas Dalkitsis

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.savvasdalkitsis.uhuruphotos.implementation.search.seam

import com.savvasdalkitsis.uhuruphotos.api.gallery.view.state.GalleryDisplay
import com.savvasdalkitsis.uhuruphotos.api.people.view.state.Person
import com.savvasdalkitsis.uhuruphotos.api.seam.Mutation
import com.savvasdalkitsis.uhuruphotos.implementation.search.view.state.SearchResults
import com.savvasdalkitsis.uhuruphotos.implementation.search.view.state.SearchState
import com.savvasdalkitsis.uhuruphotos.implementation.search.view.state.SearchSuggestion

sealed class SearchMutation(
    mutation: Mutation<SearchState>,
) : Mutation<SearchState> by mutation {

    object SwitchStateToSearching : SearchMutation({
        it.copy(searchResults = SearchResults.Searching)
    })

    object SwitchStateToIdle : SearchMutation({
        it.copy(searchResults = SearchResults.Idle)
    })

    object HideSuggestions : SearchMutation({
        it.copy(suggestion = null)
    })

    data class ChangeFeedDisplay(val display: GalleryDisplay) : SearchMutation({
        it.copy(galleryDisplay = display)
    })

    data class ChangeSearchDisplay(val display: GalleryDisplay) : SearchMutation({
        it.copy(searchDisplay = display)
    })

    data class FocusChanged(val focused: Boolean) : SearchMutation({
        it.copy(showClearButton = focused)
    })

    data class SwitchStateToFound(val found: SearchResults.Found) : SearchMutation({
        it.copy(searchResults = found)
    }) {
        override fun toString() = "Updating results with ${found.albums.size} albums"
    }

    data class ShowSearchSuggestion(val suggestion: String) : SearchMutation({
        it.copy(suggestion = suggestion)
    })

    data class ShowPeople(val people: List<Person>) : SearchMutation({
        it.copy(people = people)
    })

    data class ShowSearchSuggestions(
        val suggestions: List<SearchSuggestion>,
    ) : SearchMutation({
        it.copy(searchSuggestions = suggestions)
    })

    data class UpdateLatestQuery(val query: String) : SearchMutation({
        it.copy(latestQuery = query)
    })

    data class ShowLibrary(val showLibrary: Boolean) : SearchMutation({
        it.copy(showLibrary = showLibrary)
    })
}