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
package com.savvasdalkitsis.uhuruphotos.feature.exhibit.view.implementation.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.savvasdalkitsis.uhuruphotos.foundation.strings.api.R
import com.savvasdalkitsis.uhuruphotos.foundation.strings.api.R.string
import com.savvasdalkitsis.uhuruphotos.foundation.ui.api.ui.YesNoDialog

@Composable
internal fun DeletePermissionDialog(
    mediaItemCount: Int,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
) {
    YesNoDialog(
        title = pluralStringResource(
            R.plurals.delete_media,
            mediaItemCount,
            mediaItemCount
        ),
        onDismiss = onDismiss,
        onYes = onDelete,
        body = {
            Text(
                pluralStringResource(
                    R.plurals.delete_media_confirmation,
                    count = mediaItemCount,
                    mediaItemCount
                )
            )
            Text(stringResource(string.operation_irreverisble))
        })
}