package com.example.homemediaplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDTO {

    private Long id;

    private String name;
    private String description;

    private String eTag;
    private String youtubeId;

    private String uploadedId;
    private String uploadedETag;
}
