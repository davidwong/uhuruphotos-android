package com.savvasdalkitsis.uhuruphotos.api.albums.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.savvasdalkitsis.uhuruphotos.api.albums.ui.state.AlbumSorting
import com.savvasdalkitsis.uhuruphotos.foundation.icons.api.R.drawable
import com.savvasdalkitsis.uhuruphotos.foundation.strings.api.R.string
import com.savvasdalkitsis.uhuruphotos.foundation.ui.api.ui.DropDownActionIcon

@Composable
internal fun AlbumsSortingAction(
    sorting: AlbumSorting,
    sortingChanged: (AlbumSorting) -> Unit,
) {
    DropDownActionIcon(
        icon = when (sorting) {
            AlbumSorting.DATE_DESC -> drawable.ic_sort_date_descending
            AlbumSorting.DATE_ASC -> drawable.ic_sort_date_ascending
            AlbumSorting.ALPHABETICAL_ASC -> drawable.ic_sort_az_ascending
            AlbumSorting.ALPHABETICAL_DESC -> drawable.ic_sort_az_descending
        },
        contentDescription = stringResource(string.sorting),
    ) {
        item("Date descending") {
            sortingChanged(AlbumSorting.DATE_DESC)
        }
        item("Date ascending") {
            sortingChanged(AlbumSorting.DATE_ASC)
        }
        item("Title descending") {
            sortingChanged(AlbumSorting.ALPHABETICAL_DESC)
        }
        item("Title ascending") {
            sortingChanged(AlbumSorting.ALPHABETICAL_ASC)
        }
    }
}