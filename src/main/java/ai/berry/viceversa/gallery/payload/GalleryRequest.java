package ai.berry.viceversa.gallery.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 사진 저징 및 수정 요청 객체
 */
@Data
@Builder
@AllArgsConstructor
public class GalleryRequest {

    /**
     * 타입 키
     */
    private Integer galContentTypeId;

    /**
     * 제목
     */
    private String galTitle;

    /**
     * 웹용 이미지 경로
     */
    private String galWebImageUrl;

    /**
     * 촬영 월
     */
    private String galPhotographyMonth;

    /**
     * 촬영 장소
     */
    private String galPhotographyLocation;

    /**
     * 촬영자
     */
    private String galPhotographer;

    /**
     * 검색 키워드
     */
    private String galSearchKeyword;
}
