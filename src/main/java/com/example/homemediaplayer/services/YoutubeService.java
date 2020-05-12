package com.example.homemediaplayer.services;

import com.example.homemediaplayer.entity.VideoDTO;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.util.List;

public interface YoutubeService {
    ChannelListResponse requestChannelById(String id) throws IOException;

    PlaylistItemListResponse getPlaylistItemsById(String id) throws IOException;

    PlaylistItemListResponse getPlaylistItemsByIdAndPageToken(String id, String pageToken) throws IOException;

    VideoListResponse getVideosByIds(List<String> ids) throws IOException;

    VideoListResponse getVideosByIdsAndPageToken(List<String> ids, String pageToken) throws IOException;
}
