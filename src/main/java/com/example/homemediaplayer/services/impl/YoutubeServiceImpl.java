package com.example.homemediaplayer.services.impl;

import com.example.homemediaplayer.services.YoutubeService;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YoutubeServiceImpl implements YoutubeService {

    @Value("${youtube.APIkey}")
    private String ApiKey;

    private final YouTube youTube;

    @Override
    public ChannelListResponse requestChannelById(String id) throws IOException {
        return youTube.channels().list("contentDetails")
                .setKey(ApiKey)
                .setId(id)
                .execute();
    }

    @Override
    public PlaylistItemListResponse getPlaylistItemsById(String id) throws IOException {
        return youTube.playlistItems().list("contentDetails")
                .setKey(ApiKey)
                .setId(id)
                .execute();
    }

    @Override
    public PlaylistItemListResponse getPlaylistItemsByIdAndPageToken(String id, String pageToken) throws IOException {
        return youTube.playlistItems().list("contentDetails")
                .setKey(ApiKey)
                .setId(id)
                .setPageToken(pageToken)
                .execute();
    }

    @Override
    public VideoListResponse getVideosByIds(List<String> ids) throws IOException {
        return youTube.videos().list("contentDetails, statistics, snippet")
                .setKey(ApiKey)
                .setId(String.join(",", ids))
                .execute();
    }

    @Override
    public VideoListResponse getVideosByIdsAndPageToken(List<String> ids, String pageToken) throws IOException {
        return youTube.videos().list("contentDetails, statistics, snippet")
                .setKey(ApiKey)
                .setId(String.join(",", ids))
                .setPageToken(pageToken)
                .execute();
    }
}
