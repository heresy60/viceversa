package ai.berry.viceversa.gallery.service;

import ai.berry.viceversa.gallery.entity.Gallery;
import ai.berry.viceversa.gallery.payload.GalleryRequest;
import ai.berry.viceversa.gallery.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository repository;

    /**
     * 관광 사진 정보 조회
     *
     * @param id 조회할 사진 정보의 고유 번호
     * @return 조회된 사진 정보
     */
    @Transactional(readOnly = true)
    public Gallery findById(long id) {

        return repository.findById(id).orElseThrow();
    }

    /**
     * 관광 사진 목록 조회
     *
     * @return 조회된 사진 목록 정보
     */
    @Transactional(readOnly = true)
    public List<Gallery> findAll() {

        return repository.findAll();
    }

    /**
     * 관광 사진 저장
     *
     * @param request 저장할 사진 정보
     * @return 저장된 사진 정보
     */
    @Transactional
    public Gallery save(GalleryRequest request) {

        return repository.save(Gallery.save(request));
    }

    /**
     * 관광 사진 수정
     *
     * @param id      수정할 사진 정보의 고유 번호
     * @param request 수정할 사진 관련 정보
     * @return 수정된 사진 정보
     */
    @Transactional
    public Gallery modify(long id, GalleryRequest request) {

        Gallery gallery = repository.findById(id).orElseThrow();
        gallery.modify(request);

        return repository.save(gallery);
    }

    /**
     * 관광 사진 삭제
     *
     * @param id 삭제할 사진 정보의 고유 번호
     */
    @Transactional
    public void delete(long id) {

        repository.deleteById(id);
    }
}
