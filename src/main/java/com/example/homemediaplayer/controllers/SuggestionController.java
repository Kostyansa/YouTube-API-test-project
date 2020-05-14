package com.example.homemediaplayer.controllers;

import com.example.homemediaplayer.entity.ChannelDTO;
import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.entity.VideoDTO;
import com.example.homemediaplayer.services.SuggestionService;
import com.example.homemediaplayer.services.TagService;
import com.example.homemediaplayer.services.VideoService;
import com.example.homemediaplayer.services.YoutubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SuggestionController {

    private final SuggestionService suggestionService;

    private final TagService tagService;

    @GetMapping("/")
    public ModelAndView getRoot(){
        log.trace("After Mapping {}", this);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/suggestions")
    public ModelAndView getInterface(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("suggestion");
        return modelAndView;
    }

//    @GetMapping("/suggestions/best")
//    public ModelAndView getSuggestionsBest(
//            @RequestParam String tag,
//            @RequestParam Long hours,
//            @RequestParam Long minutes,
//            @RequestParam Long seconds){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("video");
//        List<VideoDTO> videos = suggestionService.getTop5BestSuggestedVideos(
//                tagService.getTagsByNames(tag),
//                Duration.ofHours(hours).plus(Duration.ofMinutes(minutes)).plus(Duration.ofSeconds(seconds))
//                );
//        modelAndView.addObject(videos);
//        return modelAndView;
//    }
//
//    @GetMapping("/suggestions/controversial")
//    public ModelAndView getSuggestionsControversial(
//            @RequestParam String tag,
//            @RequestParam Long hours,
//            @RequestParam Long minutes,
//            @RequestParam Long seconds){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("video");
//        List<VideoDTO> videos = suggestionService.getTop5ControversialSuggestedVideos(
//                tagService.getTagsByNames(tag),
//                Duration.ofHours(hours).plus(Duration.ofMinutes(minutes)).plus(Duration.ofSeconds(seconds))
//        );
//        modelAndView.addObject(videos);
//        return modelAndView;
//    }

    @GetMapping("/suggestions/videos")
    public ModelAndView getSuggestions(
            @RequestParam String mode,
            @RequestParam String tag,
            @RequestParam Long hours,
            @RequestParam Long minutes,
            @RequestParam Long seconds){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("videos");

        List<VideoDTO> videos = null;
        List<Tag> tags = tagService.getTagsByNames(tag);
        if (mode.equals("Controversial")) {
            videos = suggestionService.getTop5ControversialSuggestedVideos(
                    tags,
                    Duration.ofHours(hours).plus(Duration.ofMinutes(minutes)).plus(Duration.ofSeconds(seconds))
            );
        }
        else if (mode.equals("Best")){
            videos = suggestionService.getTop5BestSuggestedVideos(
                    tags,
                    Duration.ofHours(hours).plus(Duration.ofMinutes(minutes)).plus(Duration.ofSeconds(seconds))
            );
        }
        modelAndView.addObject(videos);
        return modelAndView;
    }
}
