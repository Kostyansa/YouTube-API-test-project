package com.example.homemediaplayer.controllers;

import com.example.homemediaplayer.entity.ChannelDTO;
import com.example.homemediaplayer.services.ChannelService;
import com.example.homemediaplayer.services.UpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    private final UpdateService updateService;

    @GetMapping("/channels")
    public ModelAndView getChannels(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("channels");
        List<ChannelDTO> channels = channelService.getChannels();
                modelAndView.addObject(channels);
        return modelAndView;
    }

    @PostMapping("/channels")
    public ModelAndView postChannel(@RequestParam String youtubeId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("channels");
        updateService.newChannel(youtubeId);
        return modelAndView;
    }
}
