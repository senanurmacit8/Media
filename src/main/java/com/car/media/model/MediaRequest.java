package com.car.media.model;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MediaRequest {

    private Long carId;
    private Map<Long, List<String>> photoUrlAndQuestionDetailsMap;

}
