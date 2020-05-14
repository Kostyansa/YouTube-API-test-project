package com.example.homemediaplayer.services;


import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.entity.VideoDTO;

import java.time.Duration;
import java.util.List;

public interface VideoService {

    List<VideoDTO> getControversialVideosByTagsAndDuration(List<Tag> tags, Duration duration, Long limit);

    List<VideoDTO> getBestVideosByTagsAndDuration(List<Tag> tags, Duration duration, Long limit);

    VideoDTO getVideo(String youtubeId);

    void createVideoHasTags(VideoDTO videoDTO, List<Tag> tags);

    void saveVideo(VideoDTO videoDTO);

    void updateVideo(VideoDTO videoDTO);
}
