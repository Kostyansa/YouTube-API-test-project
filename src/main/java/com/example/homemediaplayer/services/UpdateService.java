package com.example.homemediaplayer.services;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;

import java.io.IOException;
import java.util.List;

public interface UpdateService {

    void update();

    void newChannel(String id);

    void addVideosFromPlaylist(PlaylistItemListResponse playlist, String id, Long channelId) throws IOException;

    void addVideos(List<PlaylistItem> items, Long channelId) throws IOException;
}
