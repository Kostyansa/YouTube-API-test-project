package com.example.homemediaplayer.services.impl;

import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.repositories.Impl.postgres.TagDAO;
import com.example.homemediaplayer.repositories.TagRepository;
import com.example.homemediaplayer.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public void saveTag(String tag) {
        tagRepository.insertTag(tag);
    }

    @Override
    public List<Tag> getTags() {
        return tagRepository.get();
    }

    @Override
    public List<Tag> getTagsByNames(String... tags) {
        return tagRepository.get(tags);
    }
}
