package com.savvasdalkitsis.uhuruphotos.implementation.gallery.page.trash.navigation

import androidx.compose.runtime.Composable
import com.savvasdalkitsis.uhuruphotos.api.gallery.page.seam.GalleryPageAction
import com.savvasdalkitsis.uhuruphotos.api.gallery.page.view.GalleryPage
import com.savvasdalkitsis.uhuruphotos.api.gallery.page.view.state.GalleryPageState
import com.savvasdalkitsis.uhuruphotos.foundation.icons.api.R.drawable
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.Either
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.Either.Right
import com.savvasdalkitsis.uhuruphotos.foundation.ui.api.view.ActionIcon
import com.savvasdalkitsis.uhuruphotos.implementation.gallery.page.trash.seam.TrashAction
import com.savvasdalkitsis.uhuruphotos.implementation.gallery.page.trash.seam.TrashAction.FingerPrintActionPressed
import com.savvasdalkitsis.uhuruphotos.implementation.gallery.page.trash.state.TrashState

@Composable
internal fun TrashAlbumPage(
    state: Pair<GalleryPageState, TrashState>,
    action: (Either<GalleryPageAction, TrashAction>) -> Unit
) {
    GalleryPage(
        state = state.first,
        additionalActionBarContent = {
            if (state.second.displayFingerPrintAction) {
                ActionIcon(
                    onClick = {
                        action(Right(FingerPrintActionPressed))
                    },
                    icon = drawable.ic_fingerprint,
                )
            }
        },
        action = {
            action(Either.Left(it))
        },
    )
}