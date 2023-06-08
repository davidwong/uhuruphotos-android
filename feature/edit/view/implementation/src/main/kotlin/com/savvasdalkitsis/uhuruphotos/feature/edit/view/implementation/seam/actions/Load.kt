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
package com.savvasdalkitsis.uhuruphotos.feature.edit.view.implementation.seam.actions

import android.net.Uri
import com.savvasdalkitsis.uhuruphotos.feature.edit.view.implementation.seam.EditActionsContext
import com.savvasdalkitsis.uhuruphotos.feature.edit.view.implementation.seam.EditMutation.SetPhotoData
import com.savvasdalkitsis.uhuruphotos.feature.edit.view.implementation.seam.effects.EditEffect
import com.savvasdalkitsis.uhuruphotos.feature.edit.view.implementation.ui.state.EditState
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.EffectHandler
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.Mutation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class Load(
    val uri: Uri,
    val name: String,
    val timestamp: Long?,
) : EditAction() {
    context(EditActionsContext) override fun handle(
        state: EditState,
        effect: EffectHandler<EditEffect>
    ): Flow<Mutation<EditState>> = flowOf(
        SetPhotoData(uri, name, timestamp),
    )

}