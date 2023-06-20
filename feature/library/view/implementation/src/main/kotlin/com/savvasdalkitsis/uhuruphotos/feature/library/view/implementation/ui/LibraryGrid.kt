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
package com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.seam.actions.AutoAlbumsSelected
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.seam.actions.FavouritePhotosSelected
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.seam.actions.HiddenPhotosSelected
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.seam.actions.ItemOrderChanged
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.seam.actions.LibraryAction
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.seam.actions.LocalBucketSelected
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.seam.actions.TrashSelected
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.seam.actions.UserAlbumsSelected
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui.state.LibraryItem.AUTO
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui.state.LibraryItem.FAVOURITE
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui.state.LibraryItem.HIDDEN
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui.state.LibraryItem.LOCAL
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui.state.LibraryItem.TRASH
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui.state.LibraryItem.USER
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui.state.LibraryLocalMedia.Found
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui.state.LibraryLocalMedia.RequiresPermissions
import com.savvasdalkitsis.uhuruphotos.feature.library.view.implementation.ui.state.LibraryState
import com.savvasdalkitsis.uhuruphotos.feature.media.common.view.api.ui.Vitrine
import com.savvasdalkitsis.uhuruphotos.feature.media.common.view.api.ui.state.VitrineState
import com.savvasdalkitsis.uhuruphotos.foundation.icons.api.R.drawable
import com.savvasdalkitsis.uhuruphotos.foundation.strings.api.R.string
import com.savvasdalkitsis.uhuruphotos.foundation.ui.api.theme.CustomColors
import com.savvasdalkitsis.uhuruphotos.foundation.ui.api.ui.SectionHeader
import dev.shreyaspatil.permissionflow.compose.rememberPermissionFlowRequestLauncher
import org.burnoutcrew.reorderable.NoDragCancelledAnimation
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.ReorderableLazyGridState
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyGridState
import org.burnoutcrew.reorderable.reorderable

@Composable
internal fun LibraryGrid(
    contentPadding: PaddingValues,
    state: LibraryState,
    action: (LibraryAction) -> Unit
) {
    val auto = stringResource(string.auto_albums)
    val user = stringResource(string.user_albums)
    val favourites = stringResource(string.favourite_photos)
    val hidden = stringResource(string.hidden_photos)
    val trash = stringResource(string.trash)
    val local = stringResource(string.local_albums)

    val permissionLauncher = rememberPermissionFlowRequestLauncher()
    if (state.items.isEmpty())
        return
    val data = remember { mutableStateOf(state.items) }
    val reordering = rememberReorderableLazyGridState(dragCancelledAnimation = NoDragCancelledAnimation(),
        onMove = { from, to ->
            data.value = data.value.toMutableList().apply {
                add(to.index, removeAt(from.index))
            }
            action(ItemOrderChanged(data.value))
        }
    )

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .reorderable(reordering)
            .detectReorderAfterLongPress(reordering),
        contentPadding = contentPadding,
        columns = GridCells.Adaptive(160.dp),
        state = reordering.gridState,
    ) {
        for (item in data.value) {
            when (item) {
                TRASH -> pillItem(reordering, trash, drawable.ic_delete, { GridItemSpan(maxCurrentLineSpan / 2) }) {
                    action(TrashSelected)
                }
                HIDDEN -> pillItem(reordering, hidden, drawable.ic_invisible, { GridItemSpan(maxCurrentLineSpan) }) {
                    action(HiddenPhotosSelected)
                }
                LOCAL -> item(local, { GridItemSpan(maxLineSpan) }) {
                    ReorderableItem(reordering, local) {
                        when (val media = state.localMedia) {
                            is Found -> LocalFolders(local, media, action)
                            is RequiresPermissions -> PillItem(local, drawable.ic_folder) {
                                permissionLauncher.launch(media.deniedPermissions.toTypedArray())
                            }
                            null -> {}
                        }
                    }
                }
                AUTO -> libraryItem(reordering, state.autoAlbums, auto) {
                    action(AutoAlbumsSelected)
                }
                USER -> libraryItem(reordering, state.userAlbums, user) {
                    action(UserAlbumsSelected)
                }
                FAVOURITE -> libraryItem(reordering, state.favouritePhotos, favourites) {
                    action(FavouritePhotosSelected)
                }
            }
        }
    }
}

@Composable
private fun LocalFolders(
    title: String,
    media: Found,
    action: (LibraryAction) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = spacedBy(8.dp),
    ) {
        SectionHeader(
            modifier = Modifier.padding(8.dp),
            title = title,
        )
        LazyRow(
            modifier = Modifier.heightIn(min = 120.dp)
        ) {
            for ((bucket, vitrineState) in media.buckets) {
                item(bucket.id) {
                    LibraryEntry(
                        modifier = Modifier.animateItemPlacement(),
                        state = vitrineState,
                        photoGridModifier = Modifier.width(120.dp),
                        title = bucket.displayName,
                    ) {
                        action(LocalBucketSelected(bucket))
                    }
                }
            }
        }
    }
}

internal fun LazyGridScope.pillItem(
    reorderable: ReorderableLazyGridState,
    title: String,
    @DrawableRes icon: Int,
    span: (LazyGridItemSpanScope.() -> GridItemSpan)? = null,
    onSelected: () -> Unit,
) {
    item(title, span) {
        ReorderableItem(reorderable, title) {
            PillItem(title, icon, onSelected)
        }
    }
}

@Composable
private fun PillItem(title: String, icon: Int, onSelected: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .clickable { onSelected() }
                .background(CustomColors.emptyItem)
                .padding(16.dp),
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center),
                painter = painterResource(icon),
                contentDescription = null
            )
        }
        Subtitle(title)
    }
}

internal fun LazyGridScope.libraryItem(
    reordering: ReorderableLazyGridState,
    vitrineState: VitrineState?,
    title: String,
    @DrawableRes overlayIcon: Int? = null,
    onSelected: () -> Unit,
) {
    vitrineState?.let {
        item(title) {
            ReorderableItem(reordering, title) {
                LibraryEntry(
                    state = vitrineState,
                    photoGridModifier = Modifier.fillMaxWidth(),
                    title = title,
                    overlayIcon = overlayIcon,
                    onSelected = onSelected
                )
            }
        }
    }
}

@Composable
fun LibraryEntry(
    modifier: Modifier = Modifier,
    state: VitrineState,
    photoGridModifier: Modifier,
    title: String,
    @DrawableRes overlayIcon: Int? = null,
    onSelected: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(8.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Vitrine(
                modifier = photoGridModifier,
                state = state,
                onSelected = onSelected,
                shape = MaterialTheme.shapes.large
            )
            if (overlayIcon != null) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(overlayIcon),
                    contentDescription = null
                )
            }
        }
        Subtitle(title)
    }
}

@Composable
private fun ColumnScope.Subtitle(title: String) {
    Text(
        modifier = Modifier
            .align(CenterHorizontally)
            .padding(4.dp),
        text = title,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.subtitle1
            .copy(fontWeight = FontWeight.Bold),
    )
}