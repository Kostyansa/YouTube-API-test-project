package com.example.homemediaplayer.services.impl;

import com.example.homemediaplayer.services.YoutubeService;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class YoutubeServiceImpl implements YoutubeService {

    @Value("${youtube.APIkey}")
    private String ApiKey;

    private final YouTube youTube;

    @Override
    public ChannelListResponse requestChannelById(String id) throws IOException {
        log.trace("Requested channel id: {}", id);
        return youTube.channels().list("contentDetails, snippet")
                .setKey(ApiKey)
                .setId(id)
                .execute();
    }

    @Override
    public PlaylistItemListResponse getPlaylistItemsById(String id) throws IOException {
        log.trace("Requested PlaylistItems for playlist id: {}", id);
        return youTube.playlistItems().list("contentDetails")
                .setKey(ApiKey)
                .setPlaylistId(id)
                .execute();
    }

    @Override
    public PlaylistItemListResponse getPlaylistItemsByIdAndPageToken(String id, String pageToken) throws IOException {
        log.trace("Requested next page of PlaylistItems for playlist id: {}, with pageToken: {}", id, pageToken);
        return youTube.playlistItems().list("contentDetails")
                .setKey(ApiKey)
                .setPlaylistId(id)
                .setPageToken(pageToken)
                .execute();
    }

    @Override
    public VideoListResponse getVideosByIds(List<String> ids) throws IOException {
        log.trace("Requested videos with ids: {}", ids);
        return youTube.videos().list("contentDetails, statistics, snippet")
                .setKey(ApiKey)
                .setId(String.join(",", ids))
                .execute();
    }

    @Override
    public VideoListResponse getVideosByIdsAndPageToken(List<String> ids, String pageToken) throws IOException {
        log.trace("Requested next page of videos with ids: {}, pageToken: {}", ids, pageToken);
        return youTube.videos().list("contentDetails, statistics, snippet")
                .setKey(ApiKey)
                .setId(String.join(",", ids))
                .setPageToken(pageToken)
                .execute();
    }
}
