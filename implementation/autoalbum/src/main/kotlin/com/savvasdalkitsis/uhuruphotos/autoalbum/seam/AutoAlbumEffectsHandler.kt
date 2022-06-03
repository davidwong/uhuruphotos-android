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
package com.savvasdalkitsis.uhuruphotos.autoalbum.seam

import com.savvasdalkitsis.uhuruphotos.api.seam.EffectHandler
import com.savvasdalkitsis.uhuruphotos.autoalbum.seam.AutoAlbumEffect.NavigateBack
import com.savvasdalkitsis.uhuruphotos.autoalbum.seam.AutoAlbumEffect.NavigateToPerson
import com.savvasdalkitsis.uhuruphotos.autoalbum.seam.AutoAlbumEffect.OpenPhotoDetails
import com.savvasdalkitsis.uhuruphotos.api.navigation.Navigator
import com.savvasdalkitsis.uhuruphotos.api.person.navigation.PersonNavigationTarget
import com.savvasdalkitsis.uhuruphotos.photos.navigation.PhotoNavigationTarget
import javax.inject.Inject

internal class AutoAlbumEffectsHandler @Inject constructor(
    private val navigator: Navigator,
) : EffectHandler<AutoAlbumEffect> {

    override suspend fun handleEffect(effect: AutoAlbumEffect) {
        when (effect) {
            is OpenPhotoDetails -> navigate(
                PhotoNavigationTarget.name(
                    effect.id,
                    effect.center,
                    effect.scale,
                    effect.video,
                )
            )
            NavigateBack -> navigator.navigateBack()
            is NavigateToPerson -> navigate(
                PersonNavigationTarget.name(effect.personId)
            )
        }
    }

    private fun navigate(name: String) {
        navigator.navigateTo(name)
    }
}