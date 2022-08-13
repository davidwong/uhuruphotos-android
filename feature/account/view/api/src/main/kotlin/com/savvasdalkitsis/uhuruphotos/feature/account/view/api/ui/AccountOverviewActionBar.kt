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
package com.savvasdalkitsis.uhuruphotos.feature.account.view.api.ui

import androidx.compose.runtime.Composable
import com.savvasdalkitsis.uhuruphotos.api.userbadge.view.UserBadge
import com.savvasdalkitsis.uhuruphotos.feature.account.view.api.seam.AccountOverviewAction
import com.savvasdalkitsis.uhuruphotos.feature.account.view.api.ui.state.AccountOverviewState

@Composable
fun AccountOverviewActionBar(
    state: AccountOverviewState,
    action: (AccountOverviewAction) -> Unit,
) {
    UserBadge(
        state = state.userInformationState,
        userBadgePressed = {
            action(AccountOverviewAction.UserBadgePressed)
        }
    )
}