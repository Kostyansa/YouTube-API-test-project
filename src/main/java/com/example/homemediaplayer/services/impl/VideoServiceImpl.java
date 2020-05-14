package com.example.homemediaplayer.services.impl;

import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.entity.VideoDTO;
import com.example.homemediaplayer.repositories.VideoRepository;
import com.example.homemediaplayer.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Override
    public List<VideoDTO> getControversialVideosByTagsAndDuration(List<Tag> tags, Duration duration, Long limit) {
        return videoRepository.getControversialVideosByTagsAndDuration(tags, duration, limit);
    }

    @Override
    public List<VideoDTO> getBestVideosByTagsAndDuration(List<Tag> tags, Duration duration, Long limit) {
        return videoRepository.getBestVideosByTagsAndDuration(tags, duration, limit);
    }

    @Override
    public VideoDTO getVideo(String youtubeId) {
        return videoRepository.getVideoByYoutubeId(youtubeId);
    }

    @Override
    public void createVideoHasTags(VideoDTO videoDTO, List<Tag> tags) {
        videoRepository.createLinksVideoHasTag(videoDTO, tags);
    }

    @Override
    public void saveVideo(VideoDTO videoDTO) {
        videoRepository.createVideo(videoDTO);
    }

    @Override
    public void updateVideo(VideoDTO videoDTO) {
        videoRepository.updateVideo(videoDTO);
    }
}
