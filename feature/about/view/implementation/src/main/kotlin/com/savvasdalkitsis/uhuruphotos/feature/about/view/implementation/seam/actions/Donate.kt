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
package com.savvasdalkitsis.uhuruphotos.feature.about.view.implementation.seam.actions

import com.savvasdalkitsis.uhuruphotos.feature.about.view.implementation.seam.AboutActionsContext
import com.savvasdalkitsis.uhuruphotos.feature.about.view.implementation.seam.effects.AboutEffect
import com.savvasdalkitsis.uhuruphotos.feature.about.view.implementation.seam.effects.NavigateToDonation
import com.savvasdalkitsis.uhuruphotos.feature.about.view.implementation.ui.state.AboutState
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.EffectHandler
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.Mutation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data object Donate : AboutAction() {
    context(AboutActionsContext) override fun handle(
        state: AboutState,
        effect: EffectHandler<AboutEffect>
    ): Flow<Mutation<AboutState>> = flow {
        effect.handleEffect(NavigateToDonation)
    }

}