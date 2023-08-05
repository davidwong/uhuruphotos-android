/*
Copyright 2023 Savvas Dalkitsis

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
package com.savvasdalkitsis.uhuruphotos.feature.videos.domain.implementation.usecase

import com.savvasdalkitsis.uhuruphotos.feature.collage.view.api.ui.state.PredefinedCollageDisplay
import com.savvasdalkitsis.uhuruphotos.feature.videos.domain.api.usecase.VideosUseCase
import com.savvasdalkitsis.uhuruphotos.foundation.preferences.api.Preferences
import com.savvasdalkitsis.uhuruphotos.foundation.preferences.api.get
import com.savvasdalkitsis.uhuruphotos.foundation.preferences.api.set
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject

@AutoBind
class VideosUseCase @Inject constructor(
    private val preferences: Preferences,
) : VideosUseCase {
    private val key = "videosGalleryDisplay"

    override fun getVideosGalleryDisplay(): PredefinedCollageDisplay =
        preferences.get(key, PredefinedCollageDisplay.default)

    override fun setVideosGalleryDisplay(galleryDisplay: PredefinedCollageDisplay) {
        preferences.set(key, galleryDisplay)
    }
}