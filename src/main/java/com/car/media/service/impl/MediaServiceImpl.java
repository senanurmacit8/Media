package com.car.media.service.impl;


import com.car.media.entity.Expert;
import com.car.media.entity.ExpertCheckPoint;
import com.car.media.entity.PhotoInfo;
import com.car.media.model.MediaRequest;
import com.car.media.model.MediaResponse;
import com.car.media.repository.ExpertCheckPointRepository;
import com.car.media.repository.ExpertRepository;
import com.car.media.repository.PhotoRepository;
import com.car.media.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ExpertCheckPointRepository expertCheckPointRepository;

    @Autowired
    ExpertRepository expertRepository;

    public MediaServiceImpl(PhotoRepository photoRepository , ExpertRepository expertRepository) {
        this.photoRepository = photoRepository;
        this.expertCheckPointRepository = expertCheckPointRepository;
        this.expertRepository = expertRepository;
    }

    /**
     * Read Metodu:
     *
     * Bu metod, &quot;carId&quot; bilgisini parametre olarak alır
     * bir dizi nesne içeren bir yanıt döndürür.
     *
     * Bu nesneler, soruları ve ayrıntıları içerir.
     *
     * Eğer bu carId ile önceki bir ekspertiz yapılmışsa
     * ve bu önceki ekspertizde "Evet, var" seçeneği işaretlenmişse, önceki ekspertizde eklenen
     * fotoğrafların URL ' leri nesnelerin içinde alt bilgi olarak döner.
     *
     * Eğer aynı araç için daha önce birden
     * fazla ekspertiz yapıldıysa, bu araç için en son yapılan ekspertize bakmak yeterli olacaktır.
     *
     * @param carId
     * @return
     */
    @Override
    public MediaResponse getReadMedia(Long carId) {
        MediaResponse mediaResponse = new MediaResponse();

        List<Expert> activeExpertList = expertRepository.findAllByCardIdAndStatus(carId,0);

        if(!CollectionUtils.isEmpty(activeExpertList)){
            mediaResponse.setExpert(activeExpertList.get(0));
        }

        return mediaResponse;
    }


    /**
     * Create Metodu:
     *
     * Bu metod, Read metodunu kullanarak oluşturulan ekspertiz ekranında sorular
     * yanıtlandıktan sonra carId bilgisini de ekleyerek çağrılır.
     *
     * Çekilen fotoğraflar otomatik olarak mobil uygulama tarafından buluta yüklenir
     * ve yazılacak olan servise, kullanıcının seçtiği cevabı ve
     * fotoğrafların URL bilgilerini içeren bir dizi string olarak iletilir.
     *
     * @param mediaRequest
     */
    @Override
    public void createMedia(MediaRequest mediaRequest) {
        Expert expert = new Expert();

        expert.setCarId(mediaRequest.getCarId());
        expert.setStatus(0);

        List<ExpertCheckPoint> expertCheckPointList = new ArrayList<>();

        for (Long mediaPhotoUrlAndQuestionDetails : mediaRequest.getPhotoUrlAndQuestionDetailsMap().keySet()){
            ExpertCheckPoint expertCheckPoint = new ExpertCheckPoint();
            List<String> photoUrlList = mediaRequest.getPhotoUrlAndQuestionDetailsMap().get(mediaPhotoUrlAndQuestionDetails);

            if(!CollectionUtils.isEmpty(photoUrlList)){
                expertCheckPoint.setActive(true);
                expertCheckPoint.setExpert(expert);
                expertCheckPoint.setQuestionId(mediaPhotoUrlAndQuestionDetails.toString());
                expertCheckPoint.setHasAdditionalData(true);
                expertCheckPoint.setLocalDateTime(LocalDateTime.now());

                List<PhotoInfo> photoInfoList = new ArrayList<>();

                for(String photoUrl: photoUrlList){
                    List<PhotoInfo> photoInfos = photoRepository.findAllByPhotoURL(photoUrl);
                    photoInfoList.addAll(photoInfos);
                }

                expertCheckPoint.setPhotoInfoList(photoInfoList);
            }

            expertCheckPointList.add(expertCheckPoint);
        }

        expert.setExpertCheckPointList(expertCheckPointList);

        expertRepository.saveAndFlush(expert);

    }

}
