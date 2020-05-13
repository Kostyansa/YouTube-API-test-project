package com.example.homemediaplayer.services;

import com.example.homemediaplayer.entity.ChannelDTO;

import java.util.List;

public interface ChannelService {

    ChannelDTO getChannel(Long id);

    ChannelDTO getChannel(String youtubeId);

    List<ChannelDTO> getChannels();

    void updateChannel(ChannelDTO channelDTO);

    int saveChannel(ChannelDTO channelDTO);
}
