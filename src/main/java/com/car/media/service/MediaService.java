package com.car.media.service;

import com.car.media.model.MediaRequest;
import com.car.media.model.MediaResponse;

public interface MediaService {

    MediaResponse getReadMedia(Long carId);

    void createMedia(MediaRequest mediaRequest);

}
