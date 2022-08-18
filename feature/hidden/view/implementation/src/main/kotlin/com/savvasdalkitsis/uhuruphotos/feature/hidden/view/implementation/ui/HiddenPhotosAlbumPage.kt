package com.savvasdalkitsis.uhuruphotos.feature.hidden.view.implementation.ui

import androidx.compose.runtime.Composable
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.seam.ShowroomAction
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.ui.ShowroomPage
import com.savvasdalkitsis.uhuruphotos.feature.showroom.view.api.ui.state.ShowroomState
import com.savvasdalkitsis.uhuruphotos.feature.hidden.view.implementation.seam.HiddenPhotosAction
import com.savvasdalkitsis.uhuruphotos.feature.hidden.view.implementation.seam.HiddenPhotosAction.FingerPrintActionPressed
import com.savvasdalkitsis.uhuruphotos.feature.hidden.view.implementation.seam.HiddenPhotosState
import com.savvasdalkitsis.uhuruphotos.foundation.icons.api.R.drawable
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.Either
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.Either.Left
import com.savvasdalkitsis.uhuruphotos.foundation.seam.api.Either.Right
import com.savvasdalkitsis.uhuruphotos.foundation.ui.api.ui.ActionIcon

@Composable
fun HiddenPhotosAlbumPage(
    state: Pair<ShowroomState, HiddenPhotosState>,
    action: (Either<ShowroomAction, HiddenPhotosAction>) -> Unit
) {
    ShowroomPage(
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
            action(Left(it))
        }
    )
}