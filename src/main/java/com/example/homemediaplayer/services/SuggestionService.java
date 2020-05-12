package com.example.homemediaplayer.services;

import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.entity.VideoDTO;

import java.time.Duration;
import java.util.List;

public interface SuggestionService {

    List<VideoDTO> getTop5BestSuggestedVideos(List<Tag> tags, Duration duration);

    List<VideoDTO> getTop5ControversialSuggestedVideos(List<Tag> tags, Duration duration);
}
