package com.example.homemediaplayer.repositories;

import com.example.homemediaplayer.entity.ChannelDTO;

import java.util.List;

public interface ChannelRepository {

    List<ChannelDTO> getChannels();
    ChannelDTO getChannel(Long id);
    ChannelDTO getChannel(String youtubeId);
    void updateChannel(ChannelDTO channelDTO);
    void createChannel(ChannelDTO channelDTO);
    void deleteChannel(Long id);
}
