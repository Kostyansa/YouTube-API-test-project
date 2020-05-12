package com.example.homemediaplayer.services.impl;

import com.example.homemediaplayer.entity.ChannelDTO;
import com.example.homemediaplayer.services.*;
import com.example.homemediaplayer.services.mapper.ChannelDTOMapper;
import com.example.homemediaplayer.services.mapper.VideoDTOMapper;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateServiceImpl implements UpdateService {

    private final TagService tagService;

    private final VideoService videoService;

    private final YoutubeService youtubeService;

    private final ChannelService channelService;

    private final ChannelDTOMapper channelDTOMapper;

    private final VideoDTOMapper videoDTOMapper;

    @Override
    public void update() {

    }

    @Override
    public void newChannel(String id) {
        try {
            ChannelDTO channel = channelDTOMapper.toChannelDTO(youtubeService.requestChannelById(id).getItems().get(0));
            PlaylistItemListResponse playlist = youtubeService.getPlaylistItemsById(channel.getUploadedId());
            channelService.saveChannel(channel);
            channel.setUploadedETag(playlist.getEtag());
            channel = channelService.getChannel(id);
            addVideosFromPlaylist(playlist, channel.getUploadedId(), channel.getId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    @Async
    @Override
    public void addVideosFromPlaylist(PlaylistItemListResponse playlist, String id, Long channelId) throws IOException {
        addVideos(playlist.getItems(), channelId);
        String nextPageToken = playlist.getNextPageToken();
        while(nextPageToken != null){
            PlaylistItemListResponse response = youtubeService.getPlaylistItemsByIdAndPageToken(id, nextPageToken);
            addVideos(response.getItems(), channelId);
            nextPageToken = response.getNextPageToken();
        }
    }

    @Async
    @Override
    public void addVideos(List<PlaylistItem> items, Long channelId) throws IOException {
        VideoListResponse response = youtubeService.getVideosByIds(
                items.stream().map(item -> item.getContentDetails().getVideoId()).collect(Collectors.toList())
        );
        for (Video video : response.getItems()){
            videoService.saveVideo(
                    videoDTOMapper.toVideoDTO(video)
            );
        }
    }


}
