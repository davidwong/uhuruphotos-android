package com.savvasdalkitsis.uhuruphotos.implementation.albums;

import com.savvasdalkitsis.uhuruphotos.api.db.media.GetPhotoSummariesForAlbum;

public class TestGetPhotoSummaries {

    private TestGetPhotoSummaries() {
        // not to be instantiated
    }

    public static final GetPhotoSummariesForAlbum photoSummariesForAlbum = new GetPhotoSummariesForAlbum(
            "photoSummariesForAlbumId",
            ""
    );
}
