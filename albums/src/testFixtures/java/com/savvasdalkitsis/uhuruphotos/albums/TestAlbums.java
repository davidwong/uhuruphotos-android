package com.savvasdalkitsis.uhuruphotos.albums;

import com.savvasdalkitsis.uhuruphotos.albums.api.model.Album;
import com.savvasdalkitsis.uhuruphotos.albums.service.model.Album.CompleteAlbum;
import com.savvasdalkitsis.uhuruphotos.albums.service.model.Album.IncompleteAlbum;
import com.savvasdalkitsis.uhuruphotos.db.albums.Albums;

import java.util.ArrayList;
import java.util.Collections;

public class TestAlbums {

    public static Album album = new Album(
            "id",
            Collections.emptyList(),
            "",
            ""
    );

    public static Albums albums = new Albums(
            "id",
            null,
            null,
            1,
            false,
            0
    );

    public static IncompleteAlbum incompleteAlbum = new IncompleteAlbum(
            "id",
            "",
            "",
            true,
            0
    );

    public static CompleteAlbum completeAlbum = new CompleteAlbum(
            "id",
            "",
            "",
            false,
            0,
            0,
            new ArrayList<>()
    );
}