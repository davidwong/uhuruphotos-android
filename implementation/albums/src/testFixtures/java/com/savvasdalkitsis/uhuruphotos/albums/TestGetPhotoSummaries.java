package com.savvasdalkitsis.uhuruphotos.albums;

import com.savvasdalkitsis.uhuruphotos.api.db.photos.GetPhotoSummariesForAlbum;

public class TestGetPhotoSummaries {

    private TestGetPhotoSummaries() {
        // not to be instantiated
    }

    public static final GetPhotoSummariesForAlbum photoSummariesForAlbum = new GetPhotoSummariesForAlbum(
            "photoSummariesForAlbumId",
            ""
    );
}