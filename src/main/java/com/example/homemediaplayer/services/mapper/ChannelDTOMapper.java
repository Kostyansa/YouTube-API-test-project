package com.example.homemediaplayer.services.mapper;

import com.example.homemediaplayer.entity.ChannelDTO;
import com.example.homemediaplayer.entity.VideoDTO;
import com.google.api.services.youtube.model.Channel;
import org.springframework.stereotype.Service;

@Service
public class ChannelDTOMapper {

    public ChannelDTO toChannelDTO(Channel channel){
        return new ChannelDTO(
                null,
                channel.getSnippet().getTitle(),
                channel.getSnippet().getDescription(),
                channel.getEtag(),
                channel.getId(),
                channel.getContentDetails().getRelatedPlaylists().getUploads(),
                null
        );
    }
}
