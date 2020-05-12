package com.example.homemediaplayer.services.mapper;

import com.example.homemediaplayer.entity.VideoDTO;
import com.google.api.services.youtube.model.Video;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class VideoDTOMapper {

    //Fuck google
    private DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public VideoDTO toVideoDTO(Video video){
        return new VideoDTO(
                null,
                video.getSnippet().getTitle(),
                video.getSnippet().getDescription(),
                LocalDateTime.parse(video.getSnippet().getPublishedAt().toStringRfc3339(), f),
                Duration.parse(video.getContentDetails().getDuration()),
                video.getStatistics().getLikeCount().longValue(),
                video.getStatistics().getDislikeCount().longValue(),
                video.getStatistics().getViewCount().longValue(),
                video.getEtag(),
                video.getId(),
                null
        );
    }
}
