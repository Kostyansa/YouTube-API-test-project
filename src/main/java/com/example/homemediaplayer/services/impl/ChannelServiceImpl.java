package com.example.homemediaplayer.services.impl;

import com.example.homemediaplayer.entity.ChannelDTO;
import com.example.homemediaplayer.repositories.ChannelRepository;
import com.example.homemediaplayer.services.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    @Override
    public ChannelDTO getChannel(Long id) {
        return channelRepository.getChannel(id);
    }

    @Override
    public ChannelDTO getChannel(String youtubeId) {
        return channelRepository.getChannel(youtubeId);
    }

    @Override
    public List<ChannelDTO> getChannels() {
        return channelRepository.getChannels();
    }

    @Override
    public void updateChannel(ChannelDTO channelDTO) {
        channelRepository.updateChannel(channelDTO);
    }

    @Override
    public int saveChannel(ChannelDTO channelDTO) {
        return channelRepository.createChannel(channelDTO);
    }
}
