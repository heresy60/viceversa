package ai.berry.viceversa.gallery.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사진 정보 조회용 객체
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GallerySearchRequest {

    /**
     * 타입 키
     */
    private Integer galContentTypeId;

    /**
     * 제목
     */
    private String galTitle;

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
