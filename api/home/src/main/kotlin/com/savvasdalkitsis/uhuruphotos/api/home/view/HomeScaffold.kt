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
package com.savvasdalkitsis.uhuruphotos.api.home.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.savvasdalkitsis.uhuruphotos.feed.view.state.FeedDisplay
import com.savvasdalkitsis.uhuruphotos.feed.view.state.FeedDisplays
import com.savvasdalkitsis.uhuruphotos.api.home.navigation.HomeNavigationBar
import com.savvasdalkitsis.uhuruphotos.api.home.navigation.NavigationStyle.BOTTOM_BAR
import com.savvasdalkitsis.uhuruphotos.api.home.navigation.NavigationStyle.NAVIGATION_RAIL
import com.savvasdalkitsis.uhuruphotos.api.home.navigation.homeNavigationStyle
import com.savvasdalkitsis.uhuruphotos.ui.view.CommonScaffold
import com.savvasdalkitsis.uhuruphotos.ui.view.Logo
import com.savvasdalkitsis.uhuruphotos.userbadge.api.view.UserBadge
import com.savvasdalkitsis.uhuruphotos.userbadge.api.view.state.UserInformationState

@Composable
fun HomeScaffold(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = { Logo() },
    navController: NavHostController,
    userInformationState: UserInformationState? = null,
    homeFeedDisplay: FeedDisplay = FeedDisplays.default,
    selectionMode: Boolean = false,
    showLibrary: Boolean = true,
    userBadgePressed: () -> Unit = {},
    actionBarContent: @Composable (RowScope.() -> Unit) = {},
    onReselected: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    CommonScaffold(
        modifier = modifier,
        title = title,
        bottomBarDisplayed = !selectionMode,
        bottomBarContent = {
            if (homeNavigationStyle() == BOTTOM_BAR) {
                HomeNavigationBar(
                    homeFeedDisplay = homeFeedDisplay,
                    showLibrary = showLibrary,
                    navController = navController,
                    onReselected = onReselected,
                )
            }
        },
        actionBarContent = {
            actionBarContent()
            userInformationState?.let {
                UserBadge(
                    state = it,
                    userBadgePressed = userBadgePressed
                )
            }
        }
    ) { contentPadding ->
        when (homeNavigationStyle()) {
            BOTTOM_BAR -> content(contentPadding)
            NAVIGATION_RAIL -> Row {
                AnimatedVisibility(visible = !selectionMode) {
                    HomeNavigationBar(
                        contentPadding = contentPadding,
                        homeFeedDisplay = homeFeedDisplay,
                        showLibrary = showLibrary,
                        navController = navController,
                        onReselected = onReselected,
                    )
                }
                content(contentPadding)
            }
        }
    }
}