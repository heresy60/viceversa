package ai.berry.viceversa.gallery.payload;

import ai.berry.viceversa.gallery.entity.Gallery;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class GalleryResponse {

    /**
     * 고유 번호
     */
    private Long galContentId;

    /**
     * 타입 번호
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

    /**
     * 최초 등록일
     */
    private String galCreatedTime;

    /**
     * 최종 수정일
     */
    private String galModifiedTime;

    public static GalleryResponse parse(Gallery gallery) {

        return GalleryResponse.builder()
                .galContentId(gallery.getGalContentId())
                .galContentTypeId(gallery.getGalContentTypeId())
                .galTitle(gallery.getGalTitle())
                .galWebImageUrl(gallery.getGalWebImageUrl())
                .galPhotographyMonth(gallery.getGalPhotographyMonth())
                .galPhotographyLocation(gallery.getGalPhotographyLocation())
                .galPhotographer(gallery.getGalPhotographer())
                .galSearchKeyword(gallery.getGalSearchKeyword())
                .galCreatedTime(gallery.getGalCreatedTime())
                .galModifiedTime(gallery.getGalModifiedTime())
                .build();
    }

}
