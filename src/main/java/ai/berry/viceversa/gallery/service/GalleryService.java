package ai.berry.viceversa.gallery.service;

import ai.berry.viceversa.gallery.entity.Gallery;
import ai.berry.viceversa.gallery.payload.GalleryRequest;
import ai.berry.viceversa.gallery.payload.GallerySearchRequest;
import ai.berry.viceversa.gallery.repository.GalleryRepository;
import ai.berry.viceversa.gallery.repository.GallerySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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

        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 사진을 찾을 수 없습니다."));
    }

    /**
     * 관광 사진 목록 조회
     *
     * @return 조회된 사진 목록 정보
     */
    @Transactional(readOnly = true)
    public Page<Gallery> findAll(GallerySearchRequest request, Pageable pageable) {

        Specification<Gallery> specification = GallerySpecification.init(request);

        return repository.findAll(specification, pageable);
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

        Gallery gallery = repository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 사진을 찾을 수 없습니다."));
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
