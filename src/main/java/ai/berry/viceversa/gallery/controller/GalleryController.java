package ai.berry.viceversa.gallery.controller;

import ai.berry.viceversa.gallery.entity.Gallery;
import ai.berry.viceversa.gallery.payload.GalleryRequest;
import ai.berry.viceversa.gallery.payload.GalleryResponse;
import ai.berry.viceversa.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/galleries")
public class GalleryController {

    private final GalleryService galleryService;

    /**
     * 사진 정보 저장
     *
     * @param request 저장할 사진 정보
     * @return 저장된 사진 정보
     */
    @PostMapping
    public ResponseEntity<GalleryResponse> create(@RequestBody GalleryRequest request) {

        Gallery gallery = galleryService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(GalleryResponse.parse(gallery));
    }

    /**
     * 사진 정보 수정
     *
     * @param id      수정할 사진 정보의 고유 번호
     * @param request 수정할 사진 관련 정보
     * @return 수정된 사진 정보
     */
    @PutMapping("/{id}")
    public ResponseEntity<GalleryResponse> modify(@PathVariable long id, @RequestBody GalleryRequest request) {

        Gallery gallery = galleryService.modify(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(GalleryResponse.parse(gallery));
    }
}
