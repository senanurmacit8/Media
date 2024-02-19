package com.car.media.service;

import com.car.media.exception.NotFoundException;
import com.car.media.model.MediaRequest;
import com.car.media.model.MediaResponse;

public interface MediaService {

    MediaResponse getReadMedia(Long carId) throws NotFoundException;

    void createMedia(MediaRequest mediaRequest);

}
