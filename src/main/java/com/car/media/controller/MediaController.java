package com.car.media.controller;


import com.car.media.model.MediaRequest;
import com.car.media.model.MediaResponse;
import com.car.media.service.impl.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MediaController {

    @Autowired
    MediaServiceImpl mediaService;

    @GetMapping(value = "/getExpert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<MediaResponse> getExpert(
            @RequestBody Long carId) {

        MediaResponse mediaResponse = mediaService.getReadMedia(carId);

        return ResponseEntity.ok(mediaResponse);
    }

    @PostMapping(value = "/createExpert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity createExpert(
            @RequestBody MediaRequest mediaRequest) {

        mediaService.createMedia(mediaRequest);

        //return ResponseEntity.created();
        return ResponseEntity.ok(null);
    }

}
