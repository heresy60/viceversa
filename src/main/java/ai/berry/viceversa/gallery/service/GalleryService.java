package ai.berry.viceversa.gallery.service;

import ai.berry.viceversa.gallery.entity.Gallery;
import ai.berry.viceversa.gallery.payload.GalleryRequest;
import ai.berry.viceversa.gallery.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository repository;

    /**
     * 관광 사진 저장
     * @param request 저장할 사진 정보
     * @return 저장된 사진 정보
     */
    @Transactional
    public Gallery save(GalleryRequest request) {

        return repository.save(Gallery.save(request));
    }
}