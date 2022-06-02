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
package com.savvasdalkitsis.uhuruphotos.library.seam

import com.savvasdalkitsis.uhuruphotos.api.autoalbum.navigation.AutoAlbumNavigationTarget
import com.savvasdalkitsis.uhuruphotos.library.seam.LibraryEffect.*
import com.savvasdalkitsis.uhuruphotos.navigation.ControllersProvider
import com.savvasdalkitsis.uhuruphotos.strings.R
import com.savvasdalkitsis.uhuruphotos.toaster.Toaster
import com.savvasdalkitsis.uhuruphotos.api.seam.EffectHandler
import javax.inject.Inject

class LibraryEffectsHandler @Inject constructor(
    private val controllersProvider: ControllersProvider,
    private val toaster: Toaster,
) : EffectHandler<LibraryEffect> {

    override suspend fun handleEffect(effect: LibraryEffect) = when (effect) {
        ErrorLoadingAutoAlbums -> toaster.show(R.string.error_loading_auto_albums)
        is NavigateToAutoAlbum -> controllersProvider.navController!!
            .navigate(AutoAlbumNavigationTarget.name(effect.album.id))
    }
}