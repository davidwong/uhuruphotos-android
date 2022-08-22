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
package com.savvasdalkitsis.uhuruphotos.feature.exhibit.view.implementation.viewmodel

import androidx.lifecycle.ViewModel
import com.savvasdalkitsis.uhuruphotos.feature.exhibit.view.implementation.seam.ExhibitAction
import com.savvasdalkitsis.uhuruphotos.feature.exhibit.view.implementation.seam.ExhibitActionHandler
import com.savvasdalkitsis.uhuruphotos.feature.exhibit.view.implementation.seam.ExhibitEffect
import com.savvasdalkitsis.uhuruphotos.feature.exhibit.view.implementation.seam.ExhibitMutation
import com.savvasdalkitsis.uhuruphotos.feature.exhibit.view.implementation.ui.state.ExhibitState
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.Seam
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.handler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExhibitViewModel @Inject constructor(
    handler: ExhibitActionHandler,
) : ViewModel(),
    Seam<ExhibitState, ExhibitEffect, ExhibitAction, ExhibitMutation> by handler(
        handler,
        ExhibitState()
    )