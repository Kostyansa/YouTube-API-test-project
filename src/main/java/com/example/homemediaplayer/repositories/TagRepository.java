package com.example.homemediaplayer.repositories;

import com.example.homemediaplayer.entity.Tag;

import java.util.List;

public interface TagRepository {

    List<Tag> get();
    Tag get(Long id);
    Tag get(String value);
    List<Tag> get(String... tags);

    void insertTag(String... values);
}
