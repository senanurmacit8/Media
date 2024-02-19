package com.car.media.service.impl;

import com.car.media.entity.Expert;
import com.car.media.entity.ExpertCheckPoint;
import com.car.media.entity.PhotoInfo;
import com.car.media.model.MediaRequest;
import com.car.media.model.MediaResponse;
import com.car.media.repository.ExpertRepository;
import com.car.media.repository.PhotoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
class MediaServiceImplTest {

    @InjectMocks
    MediaServiceImpl mediaService;

    @Mock
    ExpertRepository expertRepository;

    @Mock
    PhotoRepository photoRepository;

    @BeforeEach
    void setUp() {
        photoRepository=mock(PhotoRepository.class);
        expertRepository=mock(ExpertRepository.class);

        mediaService= new MediaServiceImpl(photoRepository,expertRepository);

    }

    @Test
    void getReadMedia() {
        Mockito.when(expertRepository.findAllByCarIdAndStatus(Mockito.anyLong(),Mockito.anyInt()))
                .thenReturn(dummyExpertList());

        MediaResponse expected = new MediaResponse();
        expected.setExpert(dummyExpertList().get(0));

        MediaResponse actual = mediaService.getReadMedia(7865L);

        Assert.assertEquals(actual.getExpert().getCarId(),expected.getExpert().getCarId());

    }

    @Test
    void createMedia() {
        Mockito.when(photoRepository.findAllByPhotoURL(Mockito.anyString())).thenReturn(dummyPhotoInfoList());

        Map<Long, List<String>> photoAndquestionMap = new HashMap<>();
        photoAndquestionMap.put(12L, Arrays.asList("https://photo1"));

        MediaRequest mediaRequest = new MediaRequest();
        mediaRequest.setCarId(7865L);
        mediaRequest.setPhotoUrlAndQuestionDetailsMap(photoAndquestionMap);

        mediaService.createMedia(mediaRequest);
    }


    private List<Expert> dummyExpertList(){
        Expert expert = new Expert();
        expert.setStatus(0);
        expert.setCarId(7865L);
        expert.setExpertCheckPointList(dummyExpertCheckPointList());

        return Arrays.asList(expert);
    }


    private List<ExpertCheckPoint> dummyExpertCheckPointList(){
        ExpertCheckPoint expertCheckPoint = new ExpertCheckPoint();
        expertCheckPoint.setPhotoInfoList(dummyPhotoInfoList());
        expertCheckPoint.setHasAdditionalData(true);
        expertCheckPoint.setQuestionId("12");
        expertCheckPoint.setLocalDateTime(LocalDateTime.now());
        expertCheckPoint.setActive(true);

        return Arrays.asList(expertCheckPoint);
    }


    private List<PhotoInfo> dummyPhotoInfoList(){
        PhotoInfo photoInfo = new PhotoInfo();
        photoInfo.setPhotoURL("https://photo1");
        photoInfo.setDescription("sağ yan çamurluk hasarlı. ");
        photoInfo.setExpertCheckPoint(null);

        return Arrays.asList(photoInfo);
    }
}