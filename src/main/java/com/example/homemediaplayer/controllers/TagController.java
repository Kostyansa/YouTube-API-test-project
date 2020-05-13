package com.example.homemediaplayer.controllers;

import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/tags")
    public ModelAndView getTags(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tags");
        List<Tag> tags = tagService.getTags();
        modelAndView.addObject(tags);
        return modelAndView;
    }
}
