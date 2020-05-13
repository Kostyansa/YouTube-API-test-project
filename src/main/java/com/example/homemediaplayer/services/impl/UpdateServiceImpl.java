package com.example.homemediaplayer.services.impl;

import com.example.homemediaplayer.entity.ChannelDTO;
import com.example.homemediaplayer.services.*;
import com.example.homemediaplayer.services.mapper.ChannelDTOMapper;
import com.example.homemediaplayer.services.mapper.VideoDTOMapper;
import com.google.api.services.youtube.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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
            ChannelListResponse response = youtubeService.requestChannelById(id);
            ChannelDTO channel = channelDTOMapper.toChannelDTO(response.getItems().get(0));
            PlaylistItemListResponse playlist = youtubeService.getPlaylistItemsById(channel.getUploadedId());
            int saved = channelService.saveChannel(channel);
            channel.setUploadedETag(playlist.getEtag());
            channel = channelService.getChannel(id);
            addVideosFromPlaylist(playlist, channel.getUploadedId(), channel);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Async
    @Override
    public void addVideosFromPlaylist(PlaylistItemListResponse playlist, String id, ChannelDTO channel) throws IOException {
        addVideos(playlist.getItems(), channel);
        String nextPageToken = playlist.getNextPageToken();
        while (nextPageToken != null) {
            PlaylistItemListResponse response = youtubeService.getPlaylistItemsByIdAndPageToken(id, nextPageToken);
            addVideos(response.getItems(), channel);
            nextPageToken = response.getNextPageToken();
        }
    }

    @Async
    @Override
    public void addVideos(List<PlaylistItem> items, ChannelDTO channel) throws IOException {
        VideoListResponse response = youtubeService.getVideosByIds(
                items.stream().map(item -> item.getContentDetails().getVideoId()).collect(Collectors.toList())
        );
        for (Video video : response.getItems()) {
            log.trace("Saving video with youtube_Id: {}", video.getId());
            videoService.saveVideo(
                    videoDTOMapper.toVideoDTO(video, channel.getId())
            );
            List<String> tags = video.getSnippet().getTags();
            if (tags != null) {
                tagService.saveTags(video.getSnippet().getTags().toArray(String[]::new));
            }
        }
    }


}
