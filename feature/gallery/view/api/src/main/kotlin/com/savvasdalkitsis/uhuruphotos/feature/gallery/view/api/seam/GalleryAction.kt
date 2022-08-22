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
package com.savvasdalkitsis.uhuruphotos.feature.gallery.view.api.seam

import androidx.compose.ui.geometry.Offset
import com.savvasdalkitsis.uhuruphotos.feature.collage.view.api.ui.state.CollageDisplay
import com.savvasdalkitsis.uhuruphotos.feature.media.common.domain.api.model.MediaItem
import com.savvasdalkitsis.uhuruphotos.feature.people.view.api.ui.state.Person

sealed class GalleryAction {
    object SwipeToRefresh : GalleryAction()
    object NavigateBack : GalleryAction()

    data class LoadCollage(val collageId: Int) : GalleryAction()
    data class SelectedMediaItem(
        val mediaItem: MediaItem,
        val center: Offset,
        val scale: Float,
    ) : GalleryAction()

    data class PersonSelected(val person: Person) : GalleryAction()
    data class ChangeCollageDisplay(val collageDisplay: CollageDisplay) : GalleryAction()
}