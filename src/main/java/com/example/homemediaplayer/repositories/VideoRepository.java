package com.example.homemediaplayer.repositories;

import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.entity.VideoDTO;

import java.time.Duration;
import java.util.List;

public interface VideoRepository {

    List<VideoDTO> getBestVideosByTagsAndDuration(List<Tag> tags, Duration duration, Long limit);

    List<VideoDTO> getControversialVideosByTagsAndDuration(List<Tag> tags, Duration duration, Long limit);

    void createVideo(VideoDTO videoDTO);

    void updateVideo(VideoDTO videoDTO);
}
