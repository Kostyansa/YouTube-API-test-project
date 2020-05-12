package com.example.homemediaplayer.services.impl;

import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.entity.VideoDTO;
import com.example.homemediaplayer.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {

    private final VideoService videoService;

    @Override
    public List<VideoDTO> getTop5BestSuggestedVideos(List<Tag> tags, Duration duration) {
        return videoService.getBestVideosByTagsAndDuration(tags, duration, 5L);
    }

    @Override
    public List<VideoDTO> getTop5ControversialSuggestedVideos(List<Tag> tags, Duration duration) {
        return videoService.getControversialVideosByTagsAndDuration(tags, duration, 5L);
    }
}
