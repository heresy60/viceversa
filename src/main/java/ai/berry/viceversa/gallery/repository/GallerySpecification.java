package ai.berry.viceversa.gallery.repository;

import ai.berry.viceversa.gallery.entity.Gallery;
import ai.berry.viceversa.gallery.payload.GallerySearchRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class GallerySpecification {

    /**
     * 초기화
     */
    public static Specification<Gallery> init(GallerySearchRequest request) {

        Specification<Gallery> specification = (root, query, criteriaBuilder) -> null;

        if (!ObjectUtils.isEmpty(request.getGalTitle())) {
            specification = specification.and(GallerySpecification.likeGalTitle(request.getGalTitle()));
        }

        if (!ObjectUtils.isEmpty(request.getGalContentTypeId())) {
            specification = specification.and(GallerySpecification.equalGalContentTypeId(request.getGalContentTypeId()));
        }

        if (!ObjectUtils.isEmpty(request.getGalPhotographyMonth())) {
            specification = specification.and(GallerySpecification.equalGalPhotographyMonth(request.getGalPhotographyMonth()));
        }

        if (!ObjectUtils.isEmpty(request.getGalPhotographyLocation())) {
            specification = specification.and(GallerySpecification.likeGalPhotographyLocation(request.getGalPhotographyLocation()));
        }

        if (!ObjectUtils.isEmpty(request.getGalPhotographer())) {
            specification = specification.and(GallerySpecification.equalGalPhotographer(request.getGalPhotographer()));
        }

        if (!ObjectUtils.isEmpty(request.getGalSearchKeyword())) {
            specification = specification.and(GallerySpecification.equalGalSearchKeyword(request.getGalSearchKeyword()));
        }

        return specification;
    }

    /**
     * 타입 번호 검색
     *
     * @param galContentTypeId 조회할 타입 번호
     */
    private static Specification<Gallery> equalGalContentTypeId(int galContentTypeId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("galContentTypeId"), galContentTypeId));
    }

    /**
     * 제목 전문 검색
     *
     * @param galTitle 조회할 제목
     */
    private static Specification<Gallery> likeGalTitle(String galTitle) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("galTitle"), galTitle));
    }

    /**
     * 촬영월 검색
     *
     * @param galPhotographyMonth 조회할 촬영월
     */
    private static Specification<Gallery> equalGalPhotographyMonth(String galPhotographyMonth) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("galPhotographyMonth"), galPhotographyMonth));
    }

    /**
     * 쵤영 장소 전문 검색
     * @param galPhotographyLocation 조회할 촬영 장소
     */
    private static Specification<Gallery> likeGalPhotographyLocation(String galPhotographyLocation) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("galPhotographyLocation"), galPhotographyLocation));
    }

    /**
     * 촬영자 검색
     * @param galPhotographer 조회할 촬영자
     */
    private static Specification<Gallery> equalGalPhotographer(String galPhotographer) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("galPhotographer"), galPhotographer));
    }

    /**
     * 검색 키워드 전문 검색
     * @param galSearchKeyword 조회할 검색 키워드
     */
    private static Specification<Gallery> equalGalSearchKeyword(String galSearchKeyword) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("galSearchKeyword"), galSearchKeyword));
    }
}
