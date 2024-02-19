package com.car.media.controller;


import com.car.media.exception.NotFoundException;
import com.car.media.model.MediaRequest;
import com.car.media.model.MediaResponse;
import com.car.media.service.MediaService;
import com.car.media.service.impl.MediaServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;
import io.swagger.v3.core.util.Json;
import nonapi.io.github.classgraph.json.JSONDeserializer;
import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
public class MediaController {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MediaService mediaService;

    @GetMapping(value = "/getExpert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MediaResponse> getExpert(
            @RequestBody String carId) throws JsonProcessingException, NotFoundException {
        MediaResponse mediaResponse = null;

        if(!StringUtils.isNullOrEmpty(carId)){
            Map<String, Object> map = objectMapper.readValue(carId, new TypeReference<Map<String,Object>>(){});

             mediaResponse = mediaService.getReadMedia(Long.valueOf(map.get("carId").toString()));
        }else {
            return ResponseEntity.status(HttpStatus.valueOf("The cardId is empty !...")).body(mediaResponse);
        }

        return ResponseEntity.status(HttpStatus.OK).body(mediaResponse);
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
