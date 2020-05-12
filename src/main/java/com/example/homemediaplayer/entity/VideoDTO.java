package com.example.homemediaplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {

    private Long id;

    private String name;
    private String description;
    private LocalDateTime date;
    private Duration duration;

    private Long likes;
    private Long dislikes;
    private Long views;

    private String eTag;
    private String youtubeId;

    private Long channelId;

}
