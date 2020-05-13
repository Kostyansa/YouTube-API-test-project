package com.example.homemediaplayer.services;

import com.example.homemediaplayer.entity.Tag;

import java.util.List;

public interface TagService {

    void saveTags(String... tag);

    List<Tag> getTags();

    List<Tag> getTagsByNames(String... tags);
}
