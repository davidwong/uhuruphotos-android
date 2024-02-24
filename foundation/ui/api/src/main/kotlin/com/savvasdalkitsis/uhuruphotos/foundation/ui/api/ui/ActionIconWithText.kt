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
package com.savvasdalkitsis.uhuruphotos.foundation.ui.api.ui

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.savvasdalkitsis.uhuruphotos.foundation.icons.api.R
import com.savvasdalkitsis.uhuruphotos.foundation.theme.api.PreviewAppTheme

@Composable
fun ActionIconWithText(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    text: String,
) {
    Column(
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() },
    ) {
        ActionIcon(
            modifier = Modifier.align(CenterHorizontally),
            iconModifier = iconModifier,
            onClick = onClick,
            icon = icon,
            contentDescription = text,
        )
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 8.dp)
                .padding(horizontal = 8.dp),
            text = text,
        )
    }
}

@Composable
fun ActionIconWithText(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Drawable,
    text: String,
) {
    Column(
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() },
    ) {
        ActionIcon(
            modifier = Modifier.align(CenterHorizontally),
            iconModifier = iconModifier,
            onClick = onClick,
            icon = icon,
            contentDescription = text,
        )
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 8.dp)
                .padding(horizontal = 8.dp),
            text = text,
        )
    }
}

@Preview
@Composable
private fun ActionIconWithTextPreview() {
    PreviewAppTheme {
        ActionIconWithText(
            onClick = { },
            icon = R.drawable.ic_feed,
            text = "Text"
        )
    }
}