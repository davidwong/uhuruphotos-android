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
package com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.seam.ShowroomAction
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.seam.ShowroomAction.ChangeGalleryDisplay
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.seam.ShowroomAction.NavigateBack
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.seam.ShowroomAction.PersonSelected
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.seam.ShowroomAction.SelectedMediaItem
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.seam.ShowroomAction.SwipeToRefresh
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.ui.state.ShowroomState
import com.savvasdalkitsis.uhuruphotos.feature.gallery.view.api.ui.Gallery
import com.savvasdalkitsis.uhuruphotos.feature.gallery.view.api.ui.GalleryDisplayActionButton
import com.savvasdalkitsis.uhuruphotos.feature.people.view.api.ui.PeopleBar
import com.savvasdalkitsis.uhuruphotos.foundation.strings.api.R.string
import com.savvasdalkitsis.uhuruphotos.foundation.ui.api.ui.BackNavButton
import com.savvasdalkitsis.uhuruphotos.foundation.ui.api.ui.CommonScaffold
import com.savvasdalkitsis.uhuruphotos.foundation.ui.api.ui.NoContent

@Composable
fun ShowroomPage(
    state: ShowroomState,
    additionalActionBarContent: @Composable RowScope.() -> Unit = {},
    emptyContent: @Composable () -> Unit = { NoContent(string.no_photos) },
    action: (ShowroomAction) -> Unit
) {
    CommonScaffold(
        title = { Text(state.title.toText()) },
        expandableTopBar = true,
        navigationIcon = {
            BackNavButton {
                action(NavigateBack)
            }
        },
        actionBarContent = {
            AnimatedVisibility(state.galleryState.galleryDisplay.iconResource != 0
                    && state.galleryState.albums.isNotEmpty()) {
                GalleryDisplayActionButton(
                    onChange = { action(ChangeGalleryDisplay(it)) },
                    currentGalleryDisplay = state.galleryState.galleryDisplay
                )
            }
            additionalActionBarContent()
        }
    ) { contentPadding ->
        SwipeRefresh(
            indicatorPadding = contentPadding,
            state = rememberSwipeRefreshState(isRefreshing = state.galleryState.isLoading),
            onRefresh = { action(SwipeToRefresh) }
        ) {
            Gallery(
                contentPadding = contentPadding,
                state = state.galleryState,
                onChangeDisplay = { action(ChangeGalleryDisplay(it)) },
                galleryHeader = state.people.takeIf { it.isNotEmpty() }?.let {
                    {
                        PeopleBar(
                            modifier = Modifier.animateItemPlacement(),
                            people = state.people,
                            onPersonSelected = { action(PersonSelected(it)) }
                        )
                    }
                },
                emptyContent = emptyContent,
                onMediaItemSelected = { photo, center, scale ->
                    action(SelectedMediaItem(photo, center, scale,))
                },
            )
        }
    }
}